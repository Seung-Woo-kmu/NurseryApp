package com.example;

import com.example.api.JwtUtils;
import com.example.domain.Authority;
import com.example.domain.Gender;
import com.example.domain.Member;
import com.example.domain.nursery.CityDistrict;
import com.example.domain.nursery.Nursery;
import com.example.dto.data.PublicNurseryData;
import com.example.service.MemberService;
import com.example.service.NurseryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;
    private final NurseryService nurseryService;
    private final JwtUtils jwtUtils;

    @PostConstruct
    public void members() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member memberA = new Member("asd1qw2", encoder.encode("qwer1234sss"), "유승우", "천재", "하니어린이집", "010-1111-1111", Authority.NORMAL, Gender.MAN);
        Member memberB = new Member("qwe123qq", encoder.encode("qwer1234sss"), "유태근", "바보", "하니어린이집", "010-2222-1111", Authority.NORMAL, Gender.MAN);
        Member memberC = new Member("qwe123saa", encoder.encode("qwer1234sss"), "이지원", "개천재", "하니어린이집", "010-2222-1234", Authority.NORMAL, Gender.MAN);
        memberService.addMember(memberA);
        memberService.addMember(memberB);
        memberService.addMember(memberC);
    }

    @PostConstruct
    public void createNursery() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PublicNurseryData publicNurseryData = readChildCareInfoFromJson();
        for (PublicNurseryData.ChildCareInfo.ChildCareCenter nursery : publicNurseryData.ChildCareInfo.row) {
            nurseryService.addNursery(
                    new Nursery(
                            nursery.CRNAME,
                            CityDistrict.nameOf(nursery.SIGUNNAME),
                            nursery.CRADDR,
                            (int) nursery.EM_CNT_TOT,
                            (int) nursery.CHILD_CNT_TOT
                    )
            );
        }
    }


    private PublicNurseryData readChildCareInfoFromJson() throws IOException {
        try {
            // JSON 파일 로드
            Resource resource = new ClassPathResource("data/nurseryList.json");
            InputStream is = resource.getInputStream();

            // ObjectMapper를 사용하여 JSON을 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(is, PublicNurseryData.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("json 파일 읽어서 어린이집 데이터 생성 중에 오류 발생");
            throw e;
        }
    }
}
