package com.xrc.dsk.settings;

import static com.xrc.dsk.settings.AppParameters.MCS_HOST;
import static com.xrc.dsk.settings.AppParameters.MCS_PORT;

public class ApiEndpoints {
    public static final String ALL_MATERIALS = MCS_HOST + ":" + MCS_PORT + "/protection/all_materials";
    public static final String EQUIP_TYPE = MCS_HOST + ":" + MCS_PORT + "/radiation_type/";
    public static final String RAD_EXIT = MCS_HOST + ":" + MCS_PORT + "/calculation/kerma";
    public static final String RAD_TYPES = MCS_HOST + ":" + MCS_PORT + "/radiation_type/types";
    public static final String ROOM_CATEGORIES = MCS_HOST + ":" + MCS_PORT + "/calculation_info/room_categories";
    public static final String DIRECTION_COEFFICIENT = MCS_HOST + ":" + MCS_PORT + "/calculation_info/direction_coefficient";
    public static final String ROOM_CATEGORY = MCS_HOST + ":" + MCS_PORT + "/calculation_info/dmd?room_category=";
    public static final String DEMAND_LEAD_EQUIVALENT = MCS_HOST + ":" + MCS_PORT + "/calculation/protection";
    public static final String MAT_THICKNESS_EQUIVALENT = MCS_HOST + ":" + MCS_PORT + "/protection/material_thickness_equivalent";
    public static final String MAT_LEAD_EQUIVALENT = MCS_HOST + ":" + MCS_PORT + "/protection/material_lead_equivalent";
    public static final String ADDITIONAL_PROTECTION = MCS_HOST + ":" + MCS_PORT + "/protection/additional_protection";
    public static final String OPENINGS = MCS_HOST + ":" + MCS_PORT + "/protection/openings";
    public static final String ALL_OPENINGS = MCS_HOST + ":" + MCS_PORT + "/protection/openings/all";
}
