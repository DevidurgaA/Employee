package com.tlc.crm.contact.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.contact.api.models.LifeCycleStage;
import org.junit.Assert;
import org.junit.Test;

public class LifeCycleStageManagementTest {

    private static final LifeCycleStage LIFE_CYCLE = new LifeCycleStage();
    private static final LifeCycleStageManagement LIFE_CYCLE_STAGE_MANAGEMENT = new LifeCycleStageManagement();
    private static final JsonObject JSON_OBJECT = Json.object();

    static {
        LIFE_CYCLE.setId(1L);
        LIFE_CYCLE.setOrgId(1L);
        LIFE_CYCLE.setName("devi");

        JSON_OBJECT.put("id", LIFE_CYCLE.id());
        JSON_OBJECT.put("orgId", LIFE_CYCLE.orgId());
        JSON_OBJECT.put("name", LIFE_CYCLE.getName());
    }

    @Test
    public void jsonToModelTestConvert() {
        Assert.assertEquals(LIFE_CYCLE.id(), LIFE_CYCLE_STAGE_MANAGEMENT.convertToModel(1L, JSON_OBJECT).id());
        Assert.assertEquals(LIFE_CYCLE.orgId(), LIFE_CYCLE_STAGE_MANAGEMENT.convertToModel(1L, JSON_OBJECT).orgId());
        Assert.assertEquals(LIFE_CYCLE.getName(), LIFE_CYCLE_STAGE_MANAGEMENT.convertToModel(1L, JSON_OBJECT).getName());
    }

    @Test
    public void modelToJsonTestConvert() {
        Assert.assertEquals(JSON_OBJECT.getLong("id"), LIFE_CYCLE_STAGE_MANAGEMENT.convertToJson(LIFE_CYCLE).optLong("id", 0));
        Assert.assertEquals(JSON_OBJECT.getLong("orgId"), LIFE_CYCLE_STAGE_MANAGEMENT.convertToJson(LIFE_CYCLE).optLong("id", 0));
        Assert.assertEquals(JSON_OBJECT.getString("name"), LIFE_CYCLE_STAGE_MANAGEMENT.convertToJson(LIFE_CYCLE).optString("name", null));
    }
}