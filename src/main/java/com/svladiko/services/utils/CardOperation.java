package com.svladiko.services.utils;

/**
 * Created by Влад on 06.10.2015.
 */
public interface CardOperation {
    String BLOCK = "BLOCK";
    String UNBLOCK = "UNBLOCK_ADMIN";
    String PLEASE_UNBLOCK = "PLEASE_UNBLOCK";
    String REPLENISHMENT_AMOUNT = "REPLENISHMENT_AMOUNT";
    String PAYMENT = "PAYMENT";

    String ERROR_PAYMENT = "ERROR_PAYMENT";
    String ERROR_REPLENISHMENT = "ERROR_REPLENISHMENT";
}
