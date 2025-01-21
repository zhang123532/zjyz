package com.zjyz.pojo.param.ret;

import com.zjyz.pojo.param.req.UnitAmount;
import com.zjyz.pojo.param.req.UnitConversion;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Data
public class MaterialInfoRet {

    private Long totalNum;
    private Long current;
    private List<MaterialInfo> materialInfos;

    public void addMaterialInfo(MaterialInfo materialInfo){
        if(CollectionUtils.isEmpty(this.materialInfos)){
            this.materialInfos=new ArrayList<>();
        }
        this.materialInfos.add(materialInfo);
    }

    @Data
    public static class MaterialInfo {
        private String mid;
        private String materialName;
        private String categoryName;
        private List<UnitAmount> unitAmounts;
        private List<UnitConversion> unitConversions;
        public void addUnitAmount(UnitAmount unitAmount){
            if(CollectionUtils.isEmpty(this.unitAmounts)){
                this.unitAmounts =new ArrayList<>();
            }
            this.unitAmounts.add(unitAmount);
        }
        public void addUnitConversion(UnitConversion unitConversion){
            if(CollectionUtils.isEmpty(this.unitConversions)){
                this.unitConversions =new ArrayList<>();
            }
            this.unitConversions.add(unitConversion);
        }
    }
}
