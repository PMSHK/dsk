package com.xrc.dsk.dto.medicine;

import com.xrc.dsk.data.bin.AppData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.xrc.dsk.settings.AppParameters.MEDICINE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextFormDataDto implements Serializable, AppData {
    private String wallSign;
    private String purposeAdjacentRoom;
    private String personalCategory;

    @Override
    public String getType() {
        return MEDICINE;
    }
}
