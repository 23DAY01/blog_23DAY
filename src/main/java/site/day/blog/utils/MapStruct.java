package site.day.blog.utils;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import site.day.blog.pojo.domain.*;
import site.day.blog.pojo.dto.*;
import site.day.blog.pojo.vo.*;
import site.day.blog.pojo.vo.query.*;

import java.util.List;


/**
 * @Description JavaBean转换工具
 * @ClassName MapStruct
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
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


    /**
     * 文章
     */
    ArticlePreviewVO ArticleDTO2ArticlePreviewVO(ArticleDTO ArticleDTO);

    List<ArticlePreviewVO> ArticleDTOList2ArticlePreviewVOList(List<ArticleDTO> ArticleDTOS);

    ArticleDTO Article2ArticleDTO(Article Article);

    List<ArticleDTO> ArticleList2ArticleDTOList(List<Article> Articles);

    ArticleHomeDetailVO ArticleDTO2ArticleHomeDetailVO(ArticleDTO ArticleDTO);

    List<ArticleHomeDetailVO> ArticleDTOList2ArticleHomeDetailVOList(List<ArticleDTO> ArticleDTOS);

    ArchiveVO ArticleDTO2archiveVO(ArticleDTO ArticleDTO);

    List<ArchiveVO> ArticleDTOList2ArchiveVOList(List<ArticleDTO> ArticleDTOS);

    ArticleHomeVO ArticleDTO2ArticleHomeVO(ArticleDTO ArticleDTO);

    List<ArticleHomeVO> ArticleDTOList2ArticleHomeVOList(List<ArticleDTO> ArticleDTOS);

    ArticleRecommendVO ArticleDTO2ArticleRecommendVO(ArticleDTO ArticleDTO);

    List<ArticleRecommendVO> ArticleDTOList2ArticleRecommendVOList(List<ArticleDTO> ArticleDTOS);

    ArticleBackVO ArticleDTO2ArticleBackVO(ArticleDTO ArticleDTO);

    List<ArticleBackVO> ArticleDTOList2ArticleBackVOList(List<ArticleDTO> ArticleDTOS);

    Article ArticleSaveQuery2Article(ArticleSaveQuery articleSaveQuery);

    Article ArticleStatusQuery2Article(ArticleStatusQuery articleStatusQuery);

    /**
     * 分类
     */
    CategoryDTO Category2CategoryDTO(Category Category);

    List<CategoryDTO> CategoryList2CategoryDTOList(List<Category> categories);

    CategoryHomeVO CategoryDTO2CategoryHomeVO(CategoryDTO CategoryDTO);

    List<CategoryHomeVO> CategoryDTOList2CategoryHomeVOList(List<CategoryDTO> CategoryDTOS);

    List<CategoryBackVO> CategoryDTOList2CategoryBackVOList(List<CategoryDTO> categoryDTOList);

    Category CategorySaveQuery2Category(CategorySaveQuery categorySaveQuery);

    //CategoryBackVO CategoryDTO2CategoryVO(CategoryDTO CategoryDTO);
    //
    //List<CategoryBackVO> CategoryDTOList2CategoryVOList(List<CategoryDTO> CategoryDTOS);

    /**
     * 评论
     */
    CommentDTO Comment2CommentDTO(Comment Comment);

    List<CommentDTO> CommentList2CommentDTOList(List<Comment> Comments);

    CommentBackVO CommentDTO2CommentBackVO(CommentDTO CommentDTO);

    List<CommentBackVO> CommentDTOList2CommentBackVOList(List<CommentDTO> CommentDTOS);

    CommentHomeVO CommentDTO2CommentHomeVO(CommentDTO CommentDTO);

    List<CommentHomeVO> CommentDTOList2CommentHomeVOList(List<CommentDTO> CommentDTOS);

    /**
     * 友链
     */
    FriendLinkDTO FriendLink2FriendLinkDTO(FriendLink FriendLink);

    List<FriendLinkDTO> FriendLinkList2FriendLinkDTOList(List<FriendLink> FriendLinks);

    FriendLinkBackVO FriendLinkDTO2FriendLinkBackVO(FriendLinkDTO FriendLinkDTO);

    List<FriendLinkBackVO> FriendLinkDTOList2FriendLinkBackVOList(List<FriendLinkDTO> FriendLinkDTOS);

    FriendLinkHomeVO FriendLinkDTO2FriendLinkHomeVO(FriendLinkDTO FriendLinkDTO);

    List<FriendLinkHomeVO> FriendLinkDTOList2FriendLinkHomeVOList(List<FriendLinkDTO> FriendLinkDTOS);

    FriendLink FriendLinkSaveQuery2Friend(FriendLinkSaveQuery friendLinkSaveQuery);


    /**
     * 菜单
     */
    MenuDTO Menu2MenuDTO(Menu Menu);

    List<MenuDTO> MenuList2MenuDTOList(List<Menu> Menus);

    MenuVO MenuDTO2MenuVO(MenuDTO MenuDTO);

    List<MenuVO> MenuDTOList2MenuVOList(List<MenuDTO> MenuDTOS);

    Menu MenuSaveQuery2Menu(MenuSaveQuery menuSaveQuery);

    /**
     * 操作日志
     */
    OperationLogDTO OperationLog2OperationLogDTO(OperationLog OperationLog);

    List<OperationLogDTO> OperationLogList2OperationLogDTOList(List<OperationLog> OperationLogs);

    OperationLogVO OperationLogDTO2OperationLogVO(OperationLogDTO OperationLogDTO);

    List<OperationLogVO> OperationLogDTOList2OperationLogVOList(List<OperationLogDTO> OperationLogDTOS);

    /**
     * 消息
     */
    MessageDTO Message2MessageDTO(Message Message);

    List<MessageDTO> MessageList2MessageDTOList(List<Message> Messages);

    MessageBackVO MessageDTO2MessageBackVO(MessageDTO MessageDTO);

    List<MessageBackVO> MessageDTOList2MessageBackVOList(List<MessageDTO> MessageDTOS);

    MessageHomeVO MessageDTO2MessageHomeVO(MessageDTO MessageDTO);

    List<MessageHomeVO> MessageDTOList2MessageHomeVOList(List<MessageDTO> MessageDTOS);

    /**
     * 页面
     */
    PageDTO Page2PageDTO(Page Page);

    List<PageDTO> PageList2PageDTOList(List<Page> Pages);

    PageVO PageDTO2PageVO(PageDTO PageDTO);

    List<PageVO> PageDTOList2PageVOList(List<PageDTO> PageDTOS);

    Page PageSaveQuery2Page(PageSaveQuery pageSaveQuery);

    /**
     * 资源
     */
    ResourceDTO Resource2ResourceDTO(Resource Resource);

    List<ResourceDTO> ResourceList2ResourceDTOList(List<Resource> Resources);

    ResourceVO ResourceDTO2ResourceVO(ResourceDTO ResourceDTO);

    List<ResourceVO> ResourceDTOList2ResourceVOList(List<ResourceDTO> ResourceDTOS);

    Resource ResourceSaveQuery2Resource(ResourceSaveQuery resourceSaveQuery);

    /**
     * 权限
     */
    RoleDTO Role2RoleDTO(Role Role);

    List<RoleDTO> RoleList2RoleDTOList(List<Role> Roles);

    RoleVO RoleDTO2RoleVO(RoleDTO RoleDTO);

    List<RoleVO> RoleDTOList2RoleVOList(List<RoleDTO> RoleDTOS);

    /**
     * 资源权限关系
     */
    RoleResourceDTO RoleResource2RoleResourceDTO(RoleResource RoleResource);

    List<RoleResourceDTO> RoleResourceList2RoleResourceDTOList(List<RoleResource> RoleResources);

    RoleResourceVO RoleResourceDTO2RoleResourceVO(RoleResourceDTO RoleResourceDTO);

    List<RoleResourceVO> RoleResourceDTOList2RoleResourceVOList(List<RoleResourceDTO> RoleResourceDTOS);

    @Mapping(target = "resourceId", source = "id")
    RoleResourceDTO resource2roleResource(Resource resource);

    List<RoleResourceDTO> resources2roleResources(List<Resource> resourceList);

    /**
     * 标签
     */
    TagDTO tag2tagDTO(Tag tag);

    List<TagDTO> tagList2tagDTOList(List<Tag> tags);

    TagHomeVO TagDTO2TagHomeVO(TagDTO tagDTO);

    List<TagHomeVO> TagDTOList2TagHomeVOList(List<TagDTO> tagDTOList);

    TagBackVO TagDTO2TagBackVO(TagDTO tagDTO);

    List<TagBackVO> TagDTOList2TagBackVOList(List<TagDTO> tagDTOList);

    Tag TagSaveQuery2Tag(TagSaveQuery tagSaveQuery);

    /**
     * 说说
     */
    TalkDTO Talk2TalkDTO(Talk Talk);

    List<TalkDTO> TalkList2TalkDTOList(List<Talk> Talks);

    TalkHomeVO TalkDTO2TalkHomeVO(TalkDTO TalkDTO);

    List<TalkHomeVO> TalkDTOList2TalkHomeVOList(List<TalkDTO> TalkDTOS);

    TalkBackVO TalkDTO2TalkBackVO(TalkDTO TalkDTO);

    List<TalkBackVO> TalkDTOList2TalkBackVOList(List<TalkDTO> TalkDTOS);


    /**
     * 用户认证
     */
    UserAuth UserAuthDTO2UserAuth(UserAuthDTO userAuthDto);

    UserAuthDTO UserAuth2UserAuthDTO(UserAuth userAuth);

    List<UserAuthDTO> UserAuthList2UserAuthDTOList(List<UserAuth> UserAuths);

    UserAuthVO UserAuthDTO2UserAuthVO(UserAuthDTO UserAuthDTO);

    List<UserAuthVO> UserAuthDTOList2UserAuthVOList(List<UserAuthDTO> UserAuthDTOS);

    UserAreaVO UserAreaDTO2UserAreaVO(UserAreaDTO userAreaDTO);

    List<UserAreaVO> UserAreaDTOList2UserAreaVOList(List<UserAreaDTO> userAreaDTOList);

    @Mappings({
            @Mapping(source = "userInfo.id", target = "userInfoId"),
            @Mapping(source = "userAuth.id", target = "userAuthId"),
            @Mapping(source = "userInfo.createTime", target = "createTime")
    })
    UserDTO UserInfo4UserAuth2UserDTO(UserInfo userInfo, UserAuth userAuth);

    UserDTO UserInfoDTO4UserAuthDTO2UserDTO(UserInfoDTO userInfoDTO, UserAuthDTO userAuthDTO);

    UserBackVO UserDTO2UserBackVO(UserDTO userDTO);

    List<UserBackVO> UserDTOList2UserBackVOList(List<UserDTO> userDTOList);

    UserRoleDTO RoleDTO2UserRoleDTO(RoleDTO roleDTO);

    List<UserRoleDTO> RoleDTOList2UserRoleDTOList(List<RoleDTO> roleDTOList);


    /**
     * 用户信息
     */

    UserInfoDTO UserInfo2UserInfoDTO(UserInfo userInfo);

    List<UserInfoDTO> UserInfoList2UserInfoDTOList(List<UserInfo> UserInfos);

    UserInfoVO UserInfoDTO2UserInfoVO(UserInfoDTO UserInfoDTO);

    List<UserInfoVO> UserInfoDTOList2UserInfoVOList(List<UserInfoDTO> UserInfoDTOS);

    UserInfo UserInfoDTO2UserInfo(UserInfoDTO userInfoDTO);

    SocialLoginDTO UserSocialLoginQuery2SocialLoginDTO(UserSocialLoginQuery userSocialLoginQuery);

    /**
     * 用户权限关系
     */
    UserRoleDTO UserRole2UserRoleDTO(UserRole UserRole);

    List<UserRoleDTO> UserRoleList2UserRoleDTOList(List<UserRole> UserRoles);

    UserRoleVO UserRoleDTO2UserRoleVO(UserRoleDTO UserRoleDTO);

    List<UserRoleVO> UserRoleDTOList2UserRoleVOList(List<UserRoleDTO> UserRoleDTOS);

    /**
     * 浏览量
     */
    ViewDTO View2ViewDTO(View View);

    List<ViewDTO> ViewList2ViewDTOList(List<View> Views);

    ViewVO ViewDTO2ViewVO(ViewDTO ViewDTO);

    List<ViewVO> ViewDTOList2ViewVOList(List<ViewDTO> ViewDTOS);

    /**
     * 网站配置
     */
    WebsiteConfigDTO WebsiteConfig2WebsiteConfigDTO(WebsiteConfig WebsiteConfig);

    List<WebsiteConfigDTO> WebsiteConfigList2WebsiteConfigDTOList(List<WebsiteConfig> WebsiteConfigs);

    WebsiteConfigVO WebsiteConfigDTO2WebsiteConfigVO(WebsiteConfigDTO WebsiteConfigDTO);

    List<WebsiteConfigVO> WebsiteConfigDTOList2WebsiteConfigVOList(List<WebsiteConfigDTO> WebsiteConfigDTOS);

    BlogBackInfoVO BlogInfoDTO2BlogBackInfoVO(BlogInfoDTO blogInfoDTO);

    List<BlogBackInfoVO> BlogInfoDTOList2BlogBackInfoVOList(List<BlogBackInfoVO> BlogBackInfoVOS);

    BlogHomeInfoVO BlogInfoDTO2BlogHomeInfoVO(BlogInfoDTO blogInfoDTO);

    List<BlogHomeInfoVO> BlogInfoDTOList2BlogHomeInfoVOList(List<BlogHomeInfoVO> blogHomeInfoVOS);

    WebsiteConfig WebsiteConfigQuery2WebsiteConfig(WebsiteConfigQuery websiteConfigQuery);

}

