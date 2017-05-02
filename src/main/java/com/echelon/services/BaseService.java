package com.echelon.services;

import com.echelon.exception.ResourceNotFoundException;
import com.echelon.exception.StateConflictException;
import com.echelon.messages.Messages;

/**
 * Created by Fahad Qureshi on 4/22/2017.
 */
public abstract class BaseService extends Messages{


    protected void throwStateConflictException(String msgId) {
        throw new StateConflictException(getMsg(msgId));

    }

    protected void throwStateConflictException(String msgId, Object... args) {
        throw new StateConflictException(getMsg(msgId, args));
    }
    protected void throwNotFoundException(String msgId) {
        throw new ResourceNotFoundException(getMsg(msgId));
    }

    protected void throwNotFoundException(String msgId, Object... args) {
        throw new ResourceNotFoundException(getMsg(msgId, args));

    }
    protected void throwNotFoundException(Object object, int entityCode, String className) {
        if (object == null) {
            throw new ResourceNotFoundException(getMsg("entity.not_found", entityCode,className));
        }
    }
}