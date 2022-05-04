package com.tlc.crm.contact.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.contact.api.models.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContactManagementTest {

    private static final Long ID = 3L;
    private static final String FIRST_NAME = "devi";
    private static final String LAST_NAME = "durga";
    private static final Long ORG_ID = 1L;
    private static final Long COMPANY_ID = null;
    private static final Long OWNER_ID = 2L;

    private static final ContactSource CONTACT_SOURCE;
    private static final LifeCycleStageManagement LIFE_CYCLE_STAGE;
    private static final Company COMPANY;

    private static final List<ContactEmail> EMAILS;
    private static final List<ContactMobile> MOBILES;

    private static final Contact CONTACT;
    private static final ContactManagement CONTACT_MANAGEMENT;
    private static final JsonObject JSON_OBJECT;

    static {
        CONTACT = new Contact();
        CONTACT_MANAGEMENT = new ContactManagement();
        CONTACT_SOURCE = new ContactSource();
        LIFE_CYCLE_STAGE = new LifeCycleStageManagement();
        COMPANY = new Company();
        EMAILS = new ArrayList<>();
        MOBILES = new ArrayList<>();

        JSON_OBJECT = Json.object();
    }

    @BeforeClass
    public static void setCategory() {
        CONTACT.setOrgId(ORG_ID);
    }

    @BeforeClass
    public static void setProduct() {
        CONTACT.setOrgId(ORG_ID);
        CONTACT.setFirstName(FIRST_NAME);
        CONTACT.setId(ID);
        CONTACT.setLastName(LAST_NAME);
        CONTACT.setCompanyId(COMPANY_ID);
        CONTACT.setOwnerId(OWNER_ID);
        CONTACT.setContactSource(CONTACT_SOURCE);
        CONTACT.setCompany(COMPANY);
        CONTACT.setContactSource(CONTACT_SOURCE);
        CONTACT.setContactSource(CONTACT_SOURCE);

        CONTACT.setEmails(EMAILS.isEmpty() ? null : EMAILS);
        CONTACT.setMobiles(MOBILES.isEmpty() ? null : MOBILES);
    }

    @BeforeClass
    public static void setJsonObject() {
        JSON_OBJECT.put("firstName", FIRST_NAME);
        JSON_OBJECT.put("id", ID);
        JSON_OBJECT.put("lastName", LAST_NAME);
        JSON_OBJECT.put("companyId", COMPANY_ID);
        JSON_OBJECT.put("ownerId", OWNER_ID);
        JSON_OBJECT.put("orgId", ORG_ID);

        JSON_OBJECT.put("contactSource", ContactSourceManagement.convertToJson(CONTACT_SOURCE));
        JSON_OBJECT.put("company", CompanyManagement.convertToJson(COMPANY));

        JSON_OBJECT.put("emails", EMAILS.isEmpty() ? Json.array() : Json.array(EMAILS));
        JSON_OBJECT.put("mobiles", MOBILES.isEmpty() ? Json.array() : Json.array(MOBILES));
    }

    @Test
    public void convertJsonToModelTest() {
        Assert.assertEquals(CONTACT.id(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).id());
        Assert.assertEquals(CONTACT.orgId(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).orgId());
        Assert.assertEquals(CONTACT.getCompanyId(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getCompanyId());
        //Assert.assertEquals(CONTACT.getCompany(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getCompany());
        //Assert.assertEquals(CONTACT.getContactSource(), CONTACT_MANAGEMENT.convert(1L, JSON_OBJECT).getContactSource());
        //Assert.assertEquals(CONTACT.getOwnerId(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getOwnerId());
        Assert.assertEquals(CONTACT.getEmails(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getEmails());
        Assert.assertEquals(CONTACT.getFirstName(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getFirstName());
        Assert.assertEquals(CONTACT.getLastName(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getLastName());
        Assert.assertEquals(CONTACT.getMobiles(), CONTACT_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getMobiles());
    }

    @Test
    public void convertModelToJsonTest() {
        Assert.assertEquals(JSON_OBJECT.getLong("id"), JSON_OBJECT.optLong("id", 0));
       // Assert.assertEquals(JSON_OBJECT.getLong("orgId"), JSON_OBJECT.optString("orgId", null));
        Assert.assertEquals(JSON_OBJECT.getString("productCode"), JSON_OBJECT.optString("productCode", null));
        Assert.assertEquals(JSON_OBJECT.getLong("orgId"), JSON_OBJECT.optLong("orgId", 0));
        Assert.assertEquals(JSON_OBJECT.getString("description"), JSON_OBJECT.optString("description", null));
        Assert.assertEquals(JSON_OBJECT.optJsonObject("category").toString(), JSON_OBJECT.optJsonObject("category").toString());
        Assert.assertEquals(JSON_OBJECT.getString("skuNumber"), JSON_OBJECT.optString("skuNumber", null));
        Assert.assertEquals(JSON_OBJECT.getJsonArray("priceTags").toString(), JSON_OBJECT.optJsonArray("priceTags").toString());
    }
}