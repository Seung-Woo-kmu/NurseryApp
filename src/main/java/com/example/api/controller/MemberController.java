package com.example.api.controller;


import com.example.api.validator.MemberValidator;
import com.example.api.validator.UpdateValidator;
import com.example.domain.Authority;
import com.example.domain.Gender;
import com.example.domain.Member;
import com.example.dto.CreateMember;
import com.example.dto.LoginMember;
import com.example.dto.LoginMemberResponse;
import com.example.dto.UpdateMember;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"유저 API"})
public class MemberController {
    @Value("${file.dir}")
    private String filePath;
    private final MemberService memberService;

    private final MemberRepository memberRepository;


    @ApiOperation(value = "유저 목록 조회")
    @GetMapping("/api/users")
    public MemberList showUsers() {
        return new MemberList(memberService.findAll()
                .stream()
                .map(u -> new MemberDto(u.getName(), u.getNickName(), u.getProfileImageUrl(), u.getNurseryName(), u.getPhoneNumber(), u.getGender()))
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/api/users/register")
    public ResponseEntity<?> addMembers(@RequestPart("file") MultipartFile file, @Validated @RequestPart CreateMember newMember, BindingResult bindingResult) throws IOException {
        if (file.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("file", "파일을 선택해야 합니다.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        MemberValidator validator = new MemberValidator(memberRepository);
        validator.validate(newMember, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors()
                    .forEach((error) -> {
                        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                    } );
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        String fileName = filePath + file.getOriginalFilename();
        File imageFile = new File(fileName);
        file.transferTo(imageFile);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = new Member(newMember.getLoginId(), passwordEncoder.encode(newMember.getPassword()), newMember.getName(), newMember.getNickName(), newMember.getNurseryName(), newMember.getPhoneNumber(), Authority.NORMAL, newMember.getGender(), file.getOriginalFilename());
        Long memberId = memberService.addMember(member);
        CreateMemberResponse response = new CreateMemberResponse(memberId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "로그인")
    @GetMapping("/api/login")
    public LoginMemberResponse login(@RequestBody @Validated LoginMember loginMember) throws Exception {
        return memberService.login(loginMember);
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/api/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    @ApiOperation(value = "이미지 저장")
    @ResponseBody
    @GetMapping("/api/users/image/{id}")
    public ResponseEntity<?> showImage(@PathVariable("id") Long id) throws IOException {
        Member member = memberService.findById(id).get();
        String profileImageUrl = member.getProfileImageUrl();
        Path path = Paths.get(profileImageUrl);
        byte[] imageBytes = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정")
    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestPart("file") MultipartFile file, @RequestPart @Validated UpdateMember request, BindingResult bindingResult) throws IOException {
        request.setId(id);
        UpdateValidator validator = new UpdateValidator(memberRepository);
        validator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors()
                    .forEach((error) -> {
                        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                    } );
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        if (file.isEmpty()) {
            memberService.update(request);
            Member member = memberService.findById(id).get();
            UpdateMemberResponse response = new UpdateMemberResponse(member.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            String fileName =filePath + file.getOriginalFilename();
            File imageFile = new File(fileName);
            file.transferTo(imageFile);
            memberService.update(request);
            memberService.updateImage(request, file.getOriginalFilename());
            Member member = memberService.findById(id).get();
            UpdateMemberResponse response = new UpdateMemberResponse(member.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
    }
    @Data
    @AllArgsConstructor
    static class MemberList<T> {
        private T members;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
        private String nickName;
        private String profileImageUrl;
        private String nurseryName;
        private String phoneNumber;
        private Gender gender;
    }
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }
}
