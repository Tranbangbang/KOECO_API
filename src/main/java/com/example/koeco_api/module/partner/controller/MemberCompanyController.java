package com.example.koeco_api.module.partner.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.common.StatusCode;
import com.example.koeco_api.module.partner.dto.request.MemberCompanyRequest;
import com.example.koeco_api.module.partner.dto.response.MemberCompanyResponse;
import com.example.koeco_api.module.partner.service.IMemberCompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "04.MemberCompany")
@RestController
@RequestMapping("/api/v1/member-company")
@RequiredArgsConstructor
public class MemberCompanyController {

    @Autowired
    IMemberCompanyService _memberCompanyService;

    @PostMapping("/create")
    public ResponseEntity<DefaultRes<MemberCompanyResponse>> createMemberCompany(
            @Valid @ModelAttribute MemberCompanyRequest request
    ) {
        MemberCompanyResponse response = _memberCompanyService.createMemberCompany(request);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.CREATED, "Thêm công ty thành viên thành công", response));
    }

    @GetMapping("/list")
    public ResponseEntity<DefaultRes<Page<MemberCompanyResponse>>> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MemberCompanyResponse> response = _memberCompanyService.getMemberCompanies(PageRequest.of(page, size));
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Danh sách công ty thành viên", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultRes<MemberCompanyResponse>> getDetail(@PathVariable Long id) {
        MemberCompanyResponse response = _memberCompanyService.getMemberCompanyDetail(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Chi tiết công ty thành viên", response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DefaultRes<MemberCompanyResponse>> update(
            @PathVariable Long id,
            @Valid @ModelAttribute MemberCompanyRequest request) {
        MemberCompanyResponse response = _memberCompanyService.updateMemberCompany(id, request);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Cập nhật công ty thành viên thành công", response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DefaultRes<Void>> delete(@PathVariable Long id) {
        _memberCompanyService.deleteMemberCompany(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Xóa công ty thành viên thành công"));
    }

    @GetMapping("/search")
    public ResponseEntity<DefaultRes<Page<MemberCompanyResponse>>> searchMemberCompanies(
            @RequestParam String companyName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MemberCompanyResponse> response = _memberCompanyService.searchMemberCompanies(companyName, PageRequest.of(page, size));
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Danh sách công ty thành viên tìm kiếm được", response));
    }
}