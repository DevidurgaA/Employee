package com.tlc.crm.contact.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.contact.api.models.Company;

import org.junit.Assert;
import org.junit.Test;

public class CompanyManagementTest {

    private static final Company COMPANY = new Company();
    private static final CompanyManagement COMPANY_MANAGEMENT= new CompanyManagement();
    private static final JsonObject JSON_OBJECT = Json.object();

    static {
        COMPANY.setId(3L);
        COMPANY.setName("1");
        COMPANY.setOrgId(3L);

        JSON_OBJECT.put("id", COMPANY.id());
        JSON_OBJECT.put("name", COMPANY.getName());
        JSON_OBJECT.put("orgId", COMPANY.orgId());
    }

    @Test
    public void jsonToModelTestConvert() {
        Assert.assertEquals(COMPANY.orgId(), COMPANY_MANAGEMENT.convertToModel(3L, JSON_OBJECT).orgId());
        Assert.assertEquals(COMPANY.id(), COMPANY_MANAGEMENT.convertToModel(3L, JSON_OBJECT).id());
        Assert.assertEquals(COMPANY.getName(), COMPANY_MANAGEMENT.convertToModel(3L, JSON_OBJECT).getName());
    }

    @Test
    public void modelToJsonTestConvert() {
        Assert.assertEquals(JSON_OBJECT.getLong("orgId"), COMPANY_MANAGEMENT.convertToJson(COMPANY).optLong("orgId", 0));
        Assert.assertEquals(JSON_OBJECT.getString("name"), COMPANY_MANAGEMENT.convertToJson(COMPANY).optString("name", null));
        Assert.assertEquals(JSON_OBJECT.getLong("id"), COMPANY_MANAGEMENT.convertToJson(COMPANY).optLong("id", 0));
    }
}