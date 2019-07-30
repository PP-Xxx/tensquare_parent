package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 更新粉丝数和关注数
     * @param userid
     * @param friendid
     * @param x
     */
    @RequestMapping(value = "/{userid}/{friendid}/{x}")
    public void incfanscountAndfollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
