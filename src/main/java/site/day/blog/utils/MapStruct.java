package site.day.blog.utils;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import site.day.blog.pojo.domain.Resource;
import site.day.blog.pojo.domain.UserAuth;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.pojo.dto.RoleResourceDTO;
import site.day.blog.pojo.dto.UserAuthDTO;
import site.day.blog.pojo.dto.UserInfoDTO;

import java.util.List;


/**
 * @Description JavaBean转换工具
 * @ClassName MapStruct
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Mapper(componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface MapStruct {

//    @Mappings({
//            @Mapping(target = "id",source = "studentId"),
//            @Mapping(target = "name",source = "studentName"),
//            @Mapping(target = "age",source = "studentAge")
//    })
//    Student studentConvert(StudentRequestVo studentRequestVo);

    //lombok和mapstruct冲突了 这个不生效
    //@Mapping(target = "d1", source = "d1")
    //Dto Domain2Dto(Domain domain);
    //
    //List<Dto> Domains2Dtos(List<Domain> domains);
    //
    //
    //@Mappings({@Mapping(target = "vos", source = "domain2s"),
    //        @Mapping(target = "voc1", source = "c1")})
    //Vo Dto2Vo(Dto dto);
    //
    //List<Vo> Dtos2Vos(List<Dto> dtos);


    /*
      资源认证  转换
     */
    @Mapping(target = "resourceId" , source = "id")
    RoleResourceDTO resource2roleResource(Resource resource);

    List<RoleResourceDTO> resources2roleResources(List<Resource> resourceList);


    /*
     * 用户认证 转换
     **/
    UserAuth userAuthDto2userAuth(UserAuthDTO userAuthDto);

    List<UserAuth> userAuthDtos2userAuths(List<UserAuthDTO> userAuthDTOS);

    UserAuthDTO userAuth2userAuthDto(UserAuth userAuth);

    List<UserAuthDTO> userAuths2userAuthDtos(List<UserAuth> userAuths);


    /*
     * 用户信息 转换
     **/

    UserInfoDTO userInfo2UserInfoDto(UserInfo userInfo);

    List<UserInfoDTO> userInfos2UserInfoDtos(List<UserInfo> userInfos);

}

