package com.lidong.spring.ioc.overview.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ls J
 * @date 2020/5/8 12:03
 * validation util
 */
public class ValidationUtil {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * valid
     *
     * @param t      bean
     * @param groups validation group
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T t, Class<?>... groups) {
        ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validate(t, groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            violationSet.forEach(violation -> result.addError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return result;
    }

    /**
     * get validate property by name
     *
     * @param obj          bean
     * @param propertyName property name
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName) {
        ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            violationSet.forEach(violation -> result.addError(propertyName, violation.getMessage()));
        }
        return result;
    }

    /**
     * valid result
     */
    public class ValidResult {

        /**
         * true if has errors
         */
        private boolean hasErrors;

        /**
         * error list
         */
        private List<ErrorMessage> errors;

        ValidResult() {
            this.errors = new ArrayList<>();
        }

        public boolean hasErrors() {
            return hasErrors;
        }

        void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        /**
         * get all errors
         *
         * @return list
         */
        public List<ErrorMessage> getAllErrors() {
            return errors;
        }

        /**
         * get all errors by String format
         *
         * @return string
         */
        public String getErrors() {
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> sb.append(error.getPropertyPath()).append(":").append(error.getMessage()).append(" "));
            return sb.toString();
        }

        void addError(String propertyName, String message) {
            this.errors.add(new ErrorMessage(propertyName, message));
        }

        public boolean isHasErrors() {
            return hasErrors;
        }

        public void setErrors(List<ErrorMessage> errors) {
            this.errors = errors;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class ErrorMessage {

        private String propertyPath;

        private String message;

        public ErrorMessage(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        public String getPropertyPath() {
            return propertyPath;
        }

        public void setPropertyPath(String propertyPath) {
            this.propertyPath = propertyPath;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
