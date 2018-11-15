package com.lenovo.ecr.hybirdcloud.uds.facede.controller;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.CloudPlatform;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.ServiceLine;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw.CloudPlatformRepository;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw.ServiceLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class ToolController {
    @Autowired
    ServiceLineRepository serviceLineRepository;

    @Autowired
    CloudPlatformRepository cloudPlatformRepository;

    @GetMapping("/v1/tool/metadata/init")
    public void metadataInit()
    {
        Map<String,String> serviceLines = new HashMap<>();
        serviceLines.put("ADV","广告");
        serviceLines.put("ASE","用户画像");
        serviceLines.put("EDU","教育");
        serviceLines.put("GAME","游戏");
        serviceLines.put("IOT","物联网引擎(IOT)");
        serviceLines.put("IRE","用户推荐");
        serviceLines.put("LCUI","语音引擎(Lcui)");
        serviceLines.put("LEGOU","乐购");
        serviceLines.put("LEX","乐窗");
        serviceLines.put("PCMGR","管家");
        serviceLines.put("RTC","数据运营");
        serviceLines.put("SHOW","想秀");
        serviceLines.put("SPEAKER","音频");
        serviceLines.put("SUS","SUS");
        serviceLines.put("UPE","数据挖掘");
        serviceLines.put("USERCENTER","用户中心");
        serviceLines.put("VIDEO","视频");
        serviceLines.put("YAO","产品推荐");
        Map<String,String> platForms = new HashMap<>();
        platForms.put("PrivateCloud","私有云");
        platForms.put("PublicCloud","公有云");
        List<ServiceLine> allServiceLines = new LinkedList<>();
        for(String enname:serviceLines.keySet())
        {
            ServiceLine serviceLine = new ServiceLine();
            serviceLine.setCnname(serviceLines.get(enname));
            serviceLine.setEnname(enname);
            allServiceLines.add(serviceLine);
        }
        serviceLineRepository.saveAll(allServiceLines);
        List<CloudPlatform> allPlatForms = new LinkedList<>();
        for(String enname:platForms.keySet())
        {
            CloudPlatform cloudPlatform = new CloudPlatform();
            cloudPlatform.setCnname(platForms.get(enname));
            cloudPlatform.setEnname(enname);
            allPlatForms.add(cloudPlatform);
        }
        cloudPlatformRepository.saveAll(allPlatForms);
    }
}
