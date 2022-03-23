
package com.actionhd.sbs.WalkandStep;

import android.content.pm.PackageManager;

import com.actionhd.sbs.WalkandStep.services.AbstractStepDetectorService;
import com.actionhd.sbs.WalkandStep.services.AccelerometerStepDetectorService;
import com.actionhd.sbs.WalkandStep.services.HardwareStepDetectorService;
import com.actionhd.sbs.WalkandStep.utils.AndroidVersionHelper;




public class Factory {



    public static Class<? extends AbstractStepDetectorService> getStepDetectorServiceClass(PackageManager pm){
        if(pm != null && AndroidVersionHelper.supportsStepDetector(pm)) {
            return HardwareStepDetectorService.class;
        }else{
            return AccelerometerStepDetectorService.class;
        }
    }
}
