package com.tlc.crm.product.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.product.api.models.Currency;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CurrencyManagementTest {

    private static final Currency CURRENCY = new Currency();
    private static final CurrencyManagement CURRENCY_MANAGEMENT = new CurrencyManagement();
    private static final JsonObject JSON_OBJECT = Json.object();

    @BeforeClass
    public static void setData() {
        CURRENCY.setId(1L);
        CURRENCY.setOrgId(1L);
        CURRENCY.setName("devi");

        JSON_OBJECT.put("id", CURRENCY.id());
        JSON_OBJECT.put("orgId", CURRENCY.orgId());
        JSON_OBJECT.put("name", CURRENCY.getName());
    }

    @Test
    public void jsonToModelTestConvert() {
        Assert.assertEquals(CURRENCY.id(), CURRENCY_MANAGEMENT.convertToModel(1L, JSON_OBJECT).id());
        Assert.assertEquals(CURRENCY.orgId(), CURRENCY_MANAGEMENT.convertToModel(1L, JSON_OBJECT).orgId());
        Assert.assertEquals(CURRENCY.getName(), CURRENCY_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getName());
    }

    @Test
    public void modelToJsonTestConvert() {
        Assert.assertEquals(JSON_OBJECT.getLong("id"), CURRENCY_MANAGEMENT.convertToJson(CURRENCY).optLong("id", 0));
        Assert.assertEquals(JSON_OBJECT.getLong("orgId"), CURRENCY_MANAGEMENT.convertToJson(CURRENCY).optLong("id", 0));
        Assert.assertEquals(JSON_OBJECT.getString("name"), CURRENCY_MANAGEMENT.convertToJson(CURRENCY).optString("name", null));
    }
}