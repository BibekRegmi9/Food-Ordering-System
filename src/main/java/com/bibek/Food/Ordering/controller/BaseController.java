package com.bibek.Food.Ordering.controller;

import com.bibek.Food.Ordering.config.CustomMessageSource;
import com.bibek.Food.Ordering.constants.SuccessConstants;
import com.bibek.Food.Ordering.enums.ResponseStatus;
import com.bibek.Food.Ordering.response.GlobalApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    /**
     * API Success ResponseStatus
     */
    protected static final ResponseStatus API_SUCCESS_STATUS = ResponseStatus.SUCCESS;

    /**
     * API Error ResponseStatus
     */
    protected static final ResponseStatus API_ERROR_STATUS = ResponseStatus.FAILURE;

    @Getter
    protected String permissionName = this.getClass().getSimpleName();
    protected String moduleName;

    /**
     * Message Source Instance
     */
    @Autowired
    protected CustomMessageSource customMessageSource;

    /**
     * Function that sends successful API Response
     *
     * @param message message
     * @param data    that is to be returned
     * @return global api response
     */
    protected GlobalApiResponse successResponse(String message, Object data) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setStatus(API_SUCCESS_STATUS);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(data);
        return globalApiResponse;
    }

    protected GlobalApiResponse successResponse(String message) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setStatus(API_SUCCESS_STATUS);
        globalApiResponse.setMessage(message);
        return globalApiResponse;
    }

    /**
     * Function that sends error API Response
     *
     * @param message
     * @param errors
     * @return
     */
    protected GlobalApiResponse errorResponse(String message, Object errors) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setStatus(API_ERROR_STATUS);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(errors);
        return globalApiResponse;
    }

    protected GlobalApiResponse successCreate(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_CREATE, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successSubmit(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_SUBMIT, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successCheck(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_CHECK, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successUpdate(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_UPDATE_MODULE, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successApprove(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_APPROVE, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successReject(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_REJECT, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }


    protected GlobalApiResponse successFetch(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_FIND_BY_ID, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successListFetch(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_FIND_LIST, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successDelete(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_DELETE, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successToggle(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_TOGGLE, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse successUpload(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get(SuccessConstants.SUCCESS_UPLOAD, customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.SUCCESS);
        return apiResponse;
    }

    protected GlobalApiResponse failureResponse(String message, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.FAILURE);
        return apiResponse;
    }

    protected GlobalApiResponse failureDelete(String moduleName, Object data) {
        GlobalApiResponse apiResponse = new GlobalApiResponse();
        String message = customMessageSource.get("ErrorConstants.ERROR_DELETE", customMessageSource.get(moduleName));
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setStatus(ResponseStatus.FAILURE);
        return apiResponse;
    }
}
