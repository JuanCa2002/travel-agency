package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.user.UserBusinessException;
import com.co.unitravel.application.exceptions.user.UserErrorCodes;
import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.domain.models.User;
import com.co.unitravel.domain.models.enums.UserStatus;
import com.co.unitravel.infrastructure.ports.in.user.UserUseCase;
import com.co.unitravel.infrastructure.ports.out.documenttype.DocumentTypePort;
import com.co.unitravel.infrastructure.ports.out.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUseImpl implements UserUseCase {

    private final UserPort userPort;

    private final DocumentTypePort documentTypePort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User create(User user) throws NotFoundException, BusinessException {
        DocumentType documentType = documentTypePort.findById(user.getDocumentType().getId());
        UserBusinessException error = new UserBusinessException();
        error.addError(UserErrorCodes.USER_DOCUMENT_NUMBER_ALREADY_EXISTS, null);
        if(userPort.existsByDocument(user.getDocumentNumber())) throw error;
        user.setId(null);
        user.setStatus(UserStatus.ACTIVO);
        user.setDocumentType(documentType);
        return userPort.save(user);
    }
}
