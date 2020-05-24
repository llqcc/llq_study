package com.liada.service.fallback;

import java.util.List;

import org.springframework.stereotype.Component;
import com.liada.service.IDeptClientService;
import com.liada.api.Dept;
import feign.hystrix.FallbackFactory;

@Component
public class IDeptClientServiceFallbackFactory
        implements
        FallbackFactory<IDeptClientService> {

    @Override
    public IDeptClientService create(Throwable cause) {
        return new IDeptClientService() {
            public Dept get(long id) {
                return null;
            }

            public String list() {
                return "consumererror";
            }

            public boolean add(Dept dept) {
                return false;
            }
        };
    }

}
