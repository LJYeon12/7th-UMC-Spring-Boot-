package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.spring.service.Memberservice.MemberCommandService;
import umc.spring.web.dto.MemberDTO.MemberRequestDTO;

@Controller
@RequiredArgsConstructor
public class MemberViewController {
    private final MemberCommandService memberCommandService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDTO.JoinDto request,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다
            return "signup";
        }
        // 바인딩 통과
        System.out.println("바인딩 통과");
        try {
            memberCommandService.joinMember(request);
            System.out.println("commandService 실행");
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정 중 에러가 발생한 경우 에러메시지 발송, and signup 페이지를 유지
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {

        model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
        return "signup";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
