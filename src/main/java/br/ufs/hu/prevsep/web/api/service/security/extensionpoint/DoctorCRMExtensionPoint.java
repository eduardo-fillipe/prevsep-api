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
                .orElseThrow(() -> new AccessDeniedException("O médico que possui esse token não está mais ativo no sistema."));

        if (!doctor.getCrm().equals(crm))
            throw new AccessDeniedException("Médico com o CRM " + doctor.getCrm() + " está tentando acessar como " + crm);

    }
}
