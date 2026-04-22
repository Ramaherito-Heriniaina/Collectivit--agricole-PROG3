package com.collectivite.Agricole.controller;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Member;
import com.collectivite.Agricole.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody Member member) {
        try {
            Member created = memberService.addMember(member);
            return new ResponseEntity<>(created, HttpStatus.CREATED);}
        catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
