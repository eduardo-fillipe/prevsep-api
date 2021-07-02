package br.ufs.hu.prevsep.web.api.service.security.extensionpoint;

import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.service.user.doctor.DoctorService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DoctorCRMExtensionPoint implements AuthorizationExtensionPoint<Integer> {
    private final DoctorService doctorService;

    public DoctorCRMExtensionPoint(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void authorize(Authentication authentication, Integer crm) throws AccessDeniedException {
        String cpf = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        DoctorResponseFullDTO doctor = doctorService.getMedic(cpf)
                .orElseThrow(() -> new AccessDeniedException("The Doctor that owns this token is not active in the system anymore."));

        if (!doctor.getCrm().equals(crm))
            throw new AccessDeniedException("Doctor with CRM " + doctor.getCrm() + " is trying to access as " + crm);

    }
}
