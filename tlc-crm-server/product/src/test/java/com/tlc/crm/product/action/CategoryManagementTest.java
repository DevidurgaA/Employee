package com.tlc.crm.product.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.product.api.models.Category;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CategoryManagementTest {

    private static Category category = new Category();
    private static CategoryManagement categoryManagement = new CategoryManagement();
    private static JsonObject jsonObject = Json.object();

    @BeforeClass
    public static void setData() {
        category.setId(1l);
        category.setOrgId(1L);
        category.setName("twozo");

        jsonObject.put("id",1l);
        jsonObject.put("name","twozo");
        jsonObject.put("orgId",category.orgId());
    }

    @Test
    public void convertJsonModelTest(){
        Assert.assertEquals(category.id(), categoryManagement.convert(jsonObject.getLong("orgId"),jsonObject).id());
        Assert.assertEquals(category.orgId(), categoryManagement.convert(jsonObject.getLong("orgId"),jsonObject).orgId());
        Assert.assertEquals(category.getName(), categoryManagement.convert(jsonObject.getLong("orgId"),jsonObject).getName());
    }

    @Test
    public void convertModelJsonTest(){
        Assert.assertEquals(jsonObject.getLong("id"), categoryManagement.convert(category).optLong("id",0));
        Assert.assertEquals(jsonObject.getLong("orgId"), categoryManagement.convert(category).optLong("orgId",0));
        Assert.assertEquals(jsonObject.getString("name"), categoryManagement.convert(category).optString("name", null));
    }
}

