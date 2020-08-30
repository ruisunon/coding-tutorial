package io.powerfulspring.utils;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorUtils {
	private ErrorUtils() {

	}

	public static String customErrors(List<ObjectError> errors) {
		JSONObject jsonObject = new JSONObject();
		try {
			String errorMesssage = "";
			jsonObject.put("status", "failure");
			jsonObject.put("title", "Field Errors");
			for (ObjectError objectError : errors) {
				if (objectError instanceof FieldError) {
					FieldError fieldError = (FieldError) objectError;
					errorMesssage += "<b>" + MethodUtils.convertString(fieldError.getField()) + " : </b>"
							+ fieldError.getDefaultMessage() + "</br>";
				}
			}
			jsonObject.put("message", errorMesssage);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	public static String captchaError() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "failure");
			jsonObject.put("title", "Field Errors");
			jsonObject.put("message", "<b>Captcha : </b>Please enter Valid Captcha.");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
