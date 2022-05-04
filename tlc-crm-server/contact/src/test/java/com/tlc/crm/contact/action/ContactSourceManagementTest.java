package com.tlc.crm.contact.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.contact.api.models.ContactSource;
import org.junit.Assert;
import org.junit.Test;

public class ContactSourceManagementTest {

    private static final ContactSource CONTACT = new ContactSource();
    private static final ContactSourceManagement CONTACT_SOURCE_MANAGEMENT = new ContactSourceManagement();
    private static final JsonObject JSON_OBJECT = Json.object();

    static {
        CONTACT.setId(1L);
        CONTACT.setOrgId(1L);
        CONTACT.setValue("devi");

        JSON_OBJECT.put("id", CONTACT.id());
        JSON_OBJECT.put("orgId", CONTACT.orgId());
        JSON_OBJECT.put("name", CONTACT.getValue());
    }

    @Test
    public void jsonToModelTestConvert() {
        Assert.assertEquals(CONTACT.id(), CONTACT_SOURCE_MANAGEMENT.convertToModel(1L, JSON_OBJECT).id());
        Assert.assertEquals(CONTACT.orgId(), CONTACT_SOURCE_MANAGEMENT.convertToModel(1L, JSON_OBJECT).orgId());
        Assert.assertEquals(CONTACT.getValue(), CONTACT_SOURCE_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getValue());
    }

    @Test
    public void modelToJsonTestConvert() {
        Assert.assertEquals(JSON_OBJECT.getLong("id"), CONTACT_SOURCE_MANAGEMENT.convertToJson(CONTACT).optLong("id", 0));
        Assert.assertEquals(JSON_OBJECT.getLong("orgId"), CONTACT_SOURCE_MANAGEMENT.convertToJson(CONTACT).optLong("orgId", 0));
        Assert.assertEquals(JSON_OBJECT.getString("name"), CONTACT_SOURCE_MANAGEMENT.convertToJson(CONTACT).optString("name", null));
    }
}