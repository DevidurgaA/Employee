package com.tlc.crm.product.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.product.api.models.Category;
import com.tlc.crm.product.api.models.PriceTag;
import com.tlc.crm.product.api.models.Product;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductManagementTest {

    private static final Long ORG_ID = 2L;
    private static final Long ID = 3L;
    private static final String NAME = "devi";
    private static final String PRODUCT_CODE = "123";
    private static final String SKU_NUMBER = "456";
    private static final String DESCRIPTION = "Hi";

    private static final Product PRODUCT;
    private static final Category CATEGORY;
    private static final List<PriceTag> PRICE_TAGS;

    private static final ProductManagement PRODUCT_MANAGEMENT;
    private static final JsonObject JSON_OBJECT;

    static {
        PRODUCT_MANAGEMENT = new ProductManagement();
        PRODUCT = new Product();
        CATEGORY = new Category();
        PRICE_TAGS = new ArrayList<>();

        JSON_OBJECT = Json.object();
    }

    @BeforeClass
    public static void setCategory() {
        CATEGORY.setOrgId(ORG_ID);
    }

    @BeforeClass
    public static void setProduct() {
        PRODUCT.setOrgId(ORG_ID);
        PRODUCT.setProductCode(PRODUCT_CODE);
        PRODUCT.setId(ID);
        PRODUCT.setName(NAME);
        PRODUCT.setCategory(CATEGORY);
        PRODUCT.setSkuNumber(SKU_NUMBER);
        PRODUCT.setDescription(DESCRIPTION);
        PRODUCT.setPriceTags(PRICE_TAGS.isEmpty() ? null : PRICE_TAGS);
    }

    @BeforeClass
    public static void setJsonObject() {
        JSON_OBJECT.put("productCode", PRODUCT_CODE);
        JSON_OBJECT.put("id", ID);
        JSON_OBJECT.put("name", NAME);
        JSON_OBJECT.put("description", DESCRIPTION);
        JSON_OBJECT.put("skuNumber", SKU_NUMBER);
        JSON_OBJECT.put("orgId", ORG_ID);
        JSON_OBJECT.put("category", CategoryManagement.convertToJson(CATEGORY));
        JSON_OBJECT.put("priceTags", PRICE_TAGS.isEmpty() ? Json.array() : Json.array(PRICE_TAGS));
    }

    @Test
    public void convertJsonToModelTest() {
        final Product product = PRODUCT_MANAGEMENT.convertJsonToModel(ORG_ID, JSON_OBJECT);

        Assert.assertEquals(PRODUCT.id(), product.id());
        Assert.assertEquals(PRODUCT.getName(), product.getName());
        Assert.assertEquals(PRODUCT.getProductCode(), product.getProductCode());
        Assert.assertEquals(PRODUCT.orgId(), product.orgId());
        Assert.assertEquals(PRODUCT.getDescription(), product.getDescription());
        Assert.assertEquals(PRODUCT.getSkuNumber(), product.getSkuNumber());
        Assert.assertEquals(PRODUCT.getCategory().orgId(), product.getCategory().orgId());
        Assert.assertEquals(PRODUCT.getPriceTags(), product.getPriceTags());
    }

    @Test
    public void convertModelToJsonTest() {
        final JsonObject jsonObject = PRODUCT_MANAGEMENT.convertModelToJson(PRODUCT);

        Assert.assertEquals(JSON_OBJECT.getLong("id"), jsonObject.optLong("id", 0));
        Assert.assertEquals(JSON_OBJECT.getString("name"), jsonObject.optString("name", null));
        Assert.assertEquals(JSON_OBJECT.getString("productCode"), jsonObject.optString("productCode", null));
        Assert.assertEquals(JSON_OBJECT.getLong("orgId"), jsonObject.optLong("orgId", 0));
        Assert.assertEquals(JSON_OBJECT.getString("description"), jsonObject.optString("description", null));
        Assert.assertEquals(JSON_OBJECT.optJsonObject("category").toString(), jsonObject.optJsonObject("category").toString());
        Assert.assertEquals(JSON_OBJECT.getString("skuNumber"), jsonObject.optString("skuNumber", null));
        Assert.assertEquals(JSON_OBJECT.getJsonArray("priceTags").toString(), jsonObject.optJsonArray("priceTags").toString());
    }
}