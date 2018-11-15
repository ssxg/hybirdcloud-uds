package com.lenovo.ecr.hybirdcloud.uds.facede.controller;

import com.lenovo.ecr.hybirdcloud.uds.common.util.DateUtil;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyCloudPlatformServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServiceLineServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.Server;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.aggregation.DailyServerAggregationRepository;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw.ServerRepository;
import com.lenovo.ecr.hybirdcloud.uds.common.util.EmptyUtil;
import com.lenovo.ecr.hybirdcloud.uds.facede.model.dto.RemoteServersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@ComponentScan("com.lenovo.ecr.hybirdcloud.uds.common.config")
@RestController
public class ExampleController {
    @Autowired
    DailyServerAggregationRepository dailyServerAggregationRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ServerRepository serverRepository;

    @GetMapping("/v1/example/hello")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("/v1/example/merror")
    public String merror() throws Exception
    {
        throw new Exception("error test!");
    }

    @GetMapping("/v1/example/server/daily/add")
    public String dailyServerAggregationAdd()
    {
        DailyServerAggregation aggregation = new DailyServerAggregation();
        aggregation.setTimestamp(DateUtil.getZero());
        List<DailyServiceLineServerAggregation> serviceLineServerAggregations  = new LinkedList<>();
        for(int i=0;i<5;i++){
            DailyServiceLineServerAggregation dailyServiceLineServerAggregation = new DailyServiceLineServerAggregation();
            dailyServiceLineServerAggregation.setCount(i+2);
            dailyServiceLineServerAggregation.setName("service"+i);
            dailyServiceLineServerAggregation.setFname("fservice"+i);
            dailyServiceLineServerAggregation.setCpu((i+2)*10);
            dailyServiceLineServerAggregation.setMemory((i+2)*20);
            serviceLineServerAggregations.add(dailyServiceLineServerAggregation);
        }
        List<DailyCloudPlatformServerAggregation> cloudPlatformServerAggregations  = new LinkedList<>();
        String[] names={"Private Cloud","Public Cloud"};
        String[] fnames={"私有云","公有云"};
        for(int i=0;i<2;i++){
            DailyCloudPlatformServerAggregation dailyCloudPlatformServerAggregation = new DailyCloudPlatformServerAggregation();
            dailyCloudPlatformServerAggregation.setCount(i+2);
            dailyCloudPlatformServerAggregation.setName(names[i%2]);
            dailyCloudPlatformServerAggregation.setFname(fnames[i%2]);
            dailyCloudPlatformServerAggregation.setCpu((i+2)*10);
            dailyCloudPlatformServerAggregation.setMemory((i+2)*20);
            cloudPlatformServerAggregations.add(dailyCloudPlatformServerAggregation);
        }
        aggregation.setServiceLines(serviceLineServerAggregations);
        aggregation.setCloudPlatforms(cloudPlatformServerAggregations);
        dailyServerAggregationRepository.save(aggregation);
        return "added";
    }

    @GetMapping("/v1/example/server/sample")
    public RemoteServersDto serverSample()
    {
        String url = "http://dashboard.lenovopcsd.com/asset/?format=json&page=1";
        ResponseEntity<RemoteServersDto> responseEntity = restTemplate.getForEntity(url, RemoteServersDto.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();
        RemoteServersDto info = responseEntity.getBody();
        List<Server> servers = new LinkedList<>();
        if(!EmptyUtil.isEmpty(info.results))
        {
            for(Server s:info.results)
            {
                s.setTimestamp(DateUtil.getZero());
                servers.add(s);
            }
        }
        serverRepository.saveAll(servers);
        return info;
    }

    @GetMapping("/v1/example/server/add")
    public String serverAdd()
    {
        long zero = DateUtil.getZero();
        Random random = new Random();
        List<Server> servers = new LinkedList<Server>();
        for(int i=0;i<100;i++)
        {
            Server s = new Server();
            s.setBrand("PublicCloud");
            s.setCpu(random.nextInt(6)+1);
            s.setMemory(random.nextInt(24)+4);
            s.setDisk(1000);
            s.setServiceline("Guanjia");
            s.setTimestamp(zero);
            servers.add(s);
        }
        serverRepository.saveAll(servers);
        return "success";
    }

    @GetMapping("/v1/example/server/list")
    public List<Server> serverList()
    {
        return serverRepository.findAll();
    }

    @GetMapping("/v1/example/server/clear")
    public String serverClear()
    {
        serverRepository.deleteAll();
        return "cleared";
    }
}
