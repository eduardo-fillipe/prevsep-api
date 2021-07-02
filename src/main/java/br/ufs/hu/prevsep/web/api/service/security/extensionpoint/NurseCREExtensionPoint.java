package br.ufs.hu.prevsep.web.api.service.security.extensionpoint;

import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.service.user.nurse.NurseService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class NurseCREExtensionPoint implements AuthorizationExtensionPoint<Integer> {
    private final NurseService nurseService;

    public NurseCREExtensionPoint(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @Override
    public void authorize(Authentication authentication, Integer cre) throws AccessDeniedException {
        String cpf = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        NurseFullDTO doctor = nurseService.getNurse(cpf)
                .orElseThrow(() -> new AccessDeniedException("The Nurse that owns this token is not active in the system anymore."));

        if (!doctor.getCre().equals(cre))
            throw new AccessDeniedException("Nurse with CRE " + doctor.getCre() + " is trying to access as " + cre);
    }
}
