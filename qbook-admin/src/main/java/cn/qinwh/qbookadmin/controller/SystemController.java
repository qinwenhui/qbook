package cn.qinwh.qbookadmin.controller;

import cn.qinwh.qbookadmin.bo.MenuBo;
import cn.qinwh.qbookadmin.bo.PermissionBo;
import cn.qinwh.qbookadmin.bo.RoleBo;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbookcommon.utils.StringUtils;
import cn.qinwh.qbooksystem.entity.*;
import cn.qinwh.qbooksystem.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/system")
public class SystemController {

    @Autowired
    @Qualifier("sysRoleServiceImpl")
    private SysRoleService sysRoleService;
    @Autowired
    @Qualifier("sysRoleMenuServiceImpl")
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    @Qualifier("sysRolePermissionServiceImpl")
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    @Qualifier("sysMenuServiceImpl")
    private SysMenuService sysMenuService;
    @Autowired
    @Qualifier("sysPermissionServiceImpl")
    private SysPermissionService sysPermissionService;

    /**
    * @Description: 批量添加用户角色
    * @Param: [userId, roleIds]
    * @return: cn.qinwh.qbookcommon.utils.ReturnMsg
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    @PostMapping("/adduserroleall")
    public ReturnMsg addUserRoleAll(Integer userId, String roleIds){
        ReturnMsg validateResult = validateNull(userId, roleIds);
        if(validateResult.getCode().equals(ReturnMsg.FAIL)){
            return validateResult;
        }
        Integer[] ids = new Integer[0];
        if(!"".equals(roleIds)){
            String[] temp = roleIds.split(",");
            ids = new Integer[temp.length];
            for(int i=0;i<temp.length;i++){
                try{
                    ids[i] = Integer.parseInt(temp[i]);
                }catch (NumberFormatException e){
                    return ReturnMsg.fail("roleIds参数有误", null);
                }
            }
        }
        boolean ok = sysRoleService.addRoleByUserId(userId, ids);
        if(ok){
            return ReturnMsg.success("添加成功", null);
        }
        return ReturnMsg.fail("添加角色失败", null);
    }
    
    /**
     * 角色列表查询
     * @param role
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/rolelist")
    public ReturnMsg roleList(SysRole role, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<SysRole> sysRoles = sysRoleService.queryListByWhere(role);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoles);
        return ReturnMsg.success("查询成功", pageInfo);
    }

    /**
     * 添加角色
     * @param bo
     * @return
     */
    @PostMapping("/addrole")
    public ReturnMsg addRole(@Valid RoleBo bo){
        SysRole role = new SysRole();
        role.setName(bo.getName());
        role.setStatus(0);
        role.setDescription(bo.getDescription());
        int updateCount = sysRoleService.save(role);
        if(updateCount == 1){
            return ReturnMsg.success("添加成功", role);
        }
        return ReturnMsg.fail("添加角色失败", null);
    }

    /**
     * 添加角色和该角色对应菜单权限
     * @param bo
     * @return
     */
    @PostMapping("/addroleandmenu")
    public ReturnMsg addRoleAndMenu(@Valid RoleBo bo, String menuIds){
        Integer[] ids = new Integer[0];
        if(StringUtils.isNotEmpty(menuIds)){
            String[] temp = menuIds.split(",");
            ids = new Integer[temp.length];
            for(int i=0;i<temp.length;i++){
                ids[i] = Integer.parseInt(temp[i]);
            }
        }
        SysRole role = new SysRole();
        role.setName(bo.getName());
        role.setStatus(0);
        role.setDescription(bo.getDescription());
        boolean updateState = sysRoleService.addRoleAndMenu(role, ids);
        if(updateState){
            return ReturnMsg.success("添加成功", role);
        }
        return ReturnMsg.fail("添加角色失败", null);
    }

    /**
     * 编辑角色
     * @param bo
     * @return
     */
    @PostMapping("/editrole")
    public ReturnMsg editRole(@Valid RoleBo bo, Integer id){
        if(id == null){
            return ReturnMsg.fail("角色编号不能为空", null);
        }
        SysRole role = new SysRole();
        role.setId(id);
        role.setName(bo.getName());
        role.setDescription(bo.getDescription());
        role.setStatus(Integer.parseInt(bo.getStatus()));
        int updateCount = sysRoleService.updateSelective(role);
        if(updateCount == 1){
            return ReturnMsg.success("编辑成功", role);
        }
        return ReturnMsg.fail("编辑角色失败", null);
    }

    /**
     * 编辑角色和该角色对应菜单权限
     * @param bo
     * @return
     */
    @PostMapping("/editroleandmenu")
    public ReturnMsg editRoleAndMenu(@Valid RoleBo bo, Integer id, String menuIds){
        if(id == null){
            return ReturnMsg.fail("角色编号不能为空", null);
        }
        Integer[] ids = new Integer[0];
        if(StringUtils.isNotEmpty(menuIds)){
            String[] temp = menuIds.split(",");
            ids = new Integer[temp.length];
            for(int i=0;i<temp.length;i++){
                ids[i] = Integer.parseInt(temp[i]);
            }
        }

        SysRole role = new SysRole();
        role.setId(id);
        role.setName(bo.getName());
        role.setDescription(bo.getDescription());
        role.setStatus(Integer.parseInt(bo.getStatus()));
        boolean updateState = sysRoleService.updateRoleAndMenu(role, ids);
        if(updateState){
            return ReturnMsg.success("编辑成功", role);
        }
        return ReturnMsg.fail("编辑角色失败", null);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @GetMapping("/deleterole")
    public ReturnMsg deleteRole(Integer id){
        if(id == null){
            return ReturnMsg.fail("id不能为空", null);
        }
        SysRole role = new SysRole();
        role.setId(id);
        int updateCount = sysRoleService.deleteByPrimaryKey(role);
        if(updateCount == 1){
            return ReturnMsg.success("删除成功", role);
        }
        return ReturnMsg.fail("删除失败", null);
    }

    /**
     * 删除角色以及对应的菜单关联
     * @param id
     * @return
     */
    @GetMapping("/deleteroleandmenu")
    public ReturnMsg deleteRoleAndMenu(Integer id){
        if(id == null){
            return ReturnMsg.fail("id不能为空", null);
        }
        boolean deleteResult = sysRoleService.deleteRoleAndMenu(id);
        if(deleteResult){
            return ReturnMsg.success("删除成功", id);
        }
        return ReturnMsg.fail("删除失败", null);
    }

    /**
     * 为角色分配菜单
     * @param roleId
     * @param menuId
     * @return
     */
    @GetMapping("/addrolemenu")
    public ReturnMsg addRoleMenu(Integer roleId, Integer menuId){
        ReturnMsg validateResult = validateNull(roleId, menuId);
        if(validateResult.getCode().equals(ReturnMsg.FAIL)){
            return validateResult;
        }
        SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        //先查询看看存不存在数据
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.queryListByWhere(roleMenu);
        roleMenu.setStatus(0);
        int updateCount = 0;
        if(roleMenuList != null && !roleMenuList.isEmpty()){
            //已经存在数据，只需要把状态改了
            roleMenu.setId(roleMenuList.get(0).getId());
            updateCount = sysRoleMenuService.updateSelective(roleMenu);
        }else{
            //不存在数据，生成
            updateCount = sysRoleMenuService.save(roleMenu);
        }
        if(updateCount == 1){
            return ReturnMsg.success("添加成功", null);
        }
        return ReturnMsg.fail("添加失败", null);
    }

    /**
     * 为角色分配菜单(批量)
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/addrolemenuall")
    public ReturnMsg addRoleMenuAll(Integer roleId, Integer[] menuIds){
        ReturnMsg validateResult = validateNull(roleId, menuIds);
        if(validateResult.getCode().equals(ReturnMsg.FAIL) || menuIds.length == 0){
            return validateResult;
        }
        for(Integer menuId: menuIds){
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            //先查询看看存不存在数据
            List<SysRoleMenu> roleMenuList = sysRoleMenuService.queryListByWhere(roleMenu);
            roleMenu.setStatus(0);
            if(roleMenuList != null && !roleMenuList.isEmpty()){
                //已经存在数据，只需要把状态改了
                roleMenu.setId(roleMenuList.get(0).getId());
                sysRoleMenuService.updateSelective(roleMenu);
            }else{
                //不存在数据，生成
                sysRoleMenuService.save(roleMenu);
            }
        }
        return ReturnMsg.success("添加成功", null);
    }

    /**
     * 为角色删除菜单项
     * @param roleId menuId
     * @return
     */
    @GetMapping("/deleterolemenu")
    public ReturnMsg deleteRoleMenu(Integer roleId, Integer menuId){
        ReturnMsg validateResult = validateNull(roleId, menuId);
        if(validateResult.getCode().equals(ReturnMsg.FAIL)){
            return validateResult;
        }
        SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        //status=1代表删除
        roleMenu.setStatus(1);
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andEqualTo("roleId", roleId).andEqualTo("menuId", menuId);
        int updateCount = sysRoleMenuService.updateByExampleSelective(roleMenu, example);
        if(updateCount == 1){
            return ReturnMsg.success("删除成功", null);
        }
        return ReturnMsg.fail("删除失败", null);
    }

    /**
     * 为角色删除菜单项（批量）
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/deleterolemenuall")
    public ReturnMsg deleteRoleMenuAll(Integer roleId, Integer[] menuIds){
        ReturnMsg validateResult = validateNull(roleId, menuIds);
        if(validateResult.getCode().equals(ReturnMsg.FAIL) || menuIds.length == 0){
            return validateResult;
        }
        for(Integer menuId: menuIds){
            SysRoleMenu roleMenu = new SysRoleMenu();
            //status=1代表删除
            roleMenu.setStatus(1);
            Example example = new Example(SysRoleMenu.class);
            example.createCriteria().andEqualTo("roleId", roleId).andEqualTo("menuId", menuId);
            sysRoleMenuService.updateByExampleSelective(roleMenu, example);
        }
        return ReturnMsg.success("删除成功", null);
    }

    /**
     * 为角色分配接口权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @GetMapping("/addrolepermission")
    public ReturnMsg addRolePermission(Integer roleId, Integer permissionId){
        ReturnMsg validateResult = validateNull(roleId, permissionId);
        if(validateResult.getCode().equals(ReturnMsg.FAIL)){
            return validateResult;
        }
        SysRolePermission rolePermission = new SysRolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        //先查询看看存不存在数据
        List<SysRolePermission> rolePermissionList = sysRolePermissionService.queryListByWhere(rolePermission);
        rolePermission.setStatus(0);
        int updateCount = 0;
        if(rolePermissionList != null && !rolePermissionList.isEmpty()){
            //已经存在数据，只需要把状态改了
            rolePermission.setId(rolePermissionList.get(0).getId());
            updateCount = sysRolePermissionService.updateSelective(rolePermission);
        }else{
            //不存在数据，生成
            updateCount = sysRolePermissionService.save(rolePermission);
        }
        if(updateCount == 1){
            return ReturnMsg.success("添加成功", null);
        }
        return ReturnMsg.fail("添加失败", null);
    }

    /**
     * 为角色分配接口权限(批量)
     * @param roleId
     * @param permissionIds
     * @return
     */
    @PostMapping("/addrolepermissionall")
    public ReturnMsg addRolePermissionAll(Integer roleId, String permissionIds){
        ReturnMsg validateResult = validateNull(roleId, permissionIds);
        if(validateResult.getCode().equals(ReturnMsg.FAIL)){
            return validateResult;
        }
        Integer[] ids = new Integer[0];
        if(!"".equals(permissionIds)){
            String[] temp = permissionIds.split(",");
            ids = new Integer[temp.length];
            for(int i=0;i<temp.length;i++){
                try{
                    ids[i] = Integer.parseInt(temp[i]);
                }catch (NumberFormatException e){
                    return ReturnMsg.fail("permissionIds参数有误", null);
                }
            }
        }
        boolean ok = sysRoleService.updateRoleAndPermission(roleId, ids);
        if(ok){
            return ReturnMsg.success("添加成功", null);
        }
        return ReturnMsg.fail("添加权限失败", null);
    }

    /**
     * 为角色删除接口权限
     * @param roleId permissionId
     * @return
     */
    @GetMapping("/deleterolepermission")
    public ReturnMsg deleteRolePermission(Integer roleId, Integer permissionId){
        ReturnMsg validateResult = validateNull(roleId, permissionId);
        if(validateResult.getCode().equals(ReturnMsg.FAIL)){
            return validateResult;
        }
        SysRolePermission rolePermission = new SysRolePermission();
        //status=1代表删除
        rolePermission.setStatus(1);
        Example example = new Example(SysRolePermission.class);
        example.createCriteria().andEqualTo("roleId", roleId).andEqualTo("permissionId", permissionId);
        int updateCount = sysRolePermissionService.updateByExampleSelective(rolePermission, example);
        if(updateCount == 1){
            return ReturnMsg.success("删除成功", null);
        }
        return ReturnMsg.fail("删除失败", null);
    }

    /**
     * 为角色删除接口权限（批量）
     * @param roleId
     * @param permissionIds
     * @return
     */
    @PostMapping("/deleterolepermissionall")
    public ReturnMsg deleteRolePermissionAll(Integer roleId, Integer[] permissionIds){
        ReturnMsg validateResult = validateNull(roleId, permissionIds);
        if(validateResult.getCode().equals(ReturnMsg.FAIL) || permissionIds.length == 0){
            return validateResult;
        }
        for(Integer permissionId: permissionIds){
            SysRolePermission rolePermission = new SysRolePermission();
            //status=1代表删除
            rolePermission.setStatus(1);
            Example example = new Example(SysRolePermission.class);
            example.createCriteria().andEqualTo("roleId", roleId).andEqualTo("permissionId", permissionId);
            sysRolePermissionService.updateByExampleSelective(rolePermission, example);
        }
        return ReturnMsg.success("删除成功", null);
    }

    /**
     * 根据角色编号获取接口列表
     * @return
     */
    @GetMapping("/permissionbyrole")
    public ReturnMsg permissionByRole(Integer roleId){
        if(roleId == null){
            return ReturnMsg.fail("角色编号不能为空", null);
        }
        List<SysPermission> permissionList = sysPermissionService.permissionByRole(roleId);
        return ReturnMsg.success("获取菜单成功", permissionList);
    }

    /**
     * 菜单列表查询
     * @param menu
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/menulist")
    public ReturnMsg menuList(SysMenu menu, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<SysMenu> sysMenus = sysMenuService.queryListByWhere(menu);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(sysMenus);
        return ReturnMsg.success("查询成功", pageInfo);
    }

    /**
     * 添加菜单
     * @param bo
     * @return
     */
    @PostMapping("/addmenu")
    public ReturnMsg addMenu(@Valid MenuBo bo){
        SysMenu menu = new SysMenu();
        menu.setName(bo.getName());
        menu.setUrl(bo.getUrl());
        menu.setPid(Integer.parseInt(bo.getPid()));
        menu.setIcon(bo.getIcon());
        menu.setSort(Integer.parseInt(bo.getSort()));
        int updateCount = sysMenuService.save(menu);
        if(updateCount == 1){
            return ReturnMsg.success("添加成功", menu);
        }
        return ReturnMsg.fail("添加失败", null);
    }

    /**
     * 编辑菜单
     * @param bo
     * @return
     */
    @PostMapping("/editmenu")
    public ReturnMsg editMenu(@Valid MenuBo bo, Integer id){
        if(id == null){
            return ReturnMsg.fail("菜单编号不能为空", null);
        }
        SysMenu menu = new SysMenu();
        menu.setId(id);
        menu.setName(bo.getName());
        menu.setUrl(bo.getUrl());
        menu.setSort(Integer.parseInt(bo.getSort()));
        menu.setIcon(bo.getIcon());
        menu.setPid(Integer.parseInt(bo.getPid()));
        int updateCount = sysMenuService.updateSelective(menu);
        if(updateCount == 1){
            return ReturnMsg.success("编辑成功", menu);
        }
        return ReturnMsg.fail("编辑菜单失败", null);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @GetMapping("/deletemenu")
    public ReturnMsg deleteMenu(Integer id){
        if(id == null){
            return ReturnMsg.fail("id不能为空", null);
        }
        SysMenu menu = new SysMenu();
        menu.setId(id);
        int updateCount = sysMenuService.deleteByPrimaryKey(menu);
        if(updateCount == 1){
            return ReturnMsg.success("删除成功", menu);
        }
        return ReturnMsg.fail("删除失败", null);
    }


    /**
     * 权限列表查询
     * @param permission
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/permissionlist")
    public ReturnMsg permissionList(SysPermission permission, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(SysPermission.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(permission.getName())){
            criteria.andLike("name", "%"+permission.getName()+"%");
        }
        if(StringUtils.isNotEmpty(permission.getDescription())){
            criteria.andLike("description", "%"+permission.getDescription()+"%");
        }
        if(StringUtils.isNotEmpty(permission.getUrl())){
            criteria.andLike("url", "%"+permission.getUrl()+"%");
        }
        List<SysPermission> sysPermission = sysPermissionService.queryListByExample(example);
        PageInfo<SysPermission> pageInfo = new PageInfo<>(sysPermission);
        return ReturnMsg.success("查询成功", pageInfo);
    }


    /**
     * 添加权限
     * @param bo
     * @return
     */
    @PostMapping("/addpermission")
    public ReturnMsg addPermission(@Valid PermissionBo bo){
        SysPermission permission = new SysPermission();
        permission.setName(bo.getName());
        permission.setUrl(bo.getUrl());
        permission.setDescription(bo.getDescription());
        permission.setStatus(0);
        int updateCount = sysPermissionService.save(permission);
        if(updateCount == 1){
            return ReturnMsg.success("添加成功", permission);
        }
        return ReturnMsg.fail("添加失败", null);
    }

    /**
     * 编辑权限
     * @param bo
     * @return
     */
    @PostMapping("/editpermission")
    public ReturnMsg editPermission(@Valid PermissionBo bo, Integer id){
        if(id == null){
            return ReturnMsg.fail("权限编号不能为空", null);
        }
        SysPermission permission = new SysPermission();
        permission.setId(id);
        permission.setName(bo.getName());
        permission.setUrl(bo.getUrl());
        permission.setDescription(bo.getDescription());
        if(bo.getStatus() != null || "".equals(bo.getStatus())){
            permission.setStatus(Integer.parseInt(bo.getStatus()));
        }
        int updateCount = sysPermissionService.updateSelective(permission);
        if(updateCount == 1){
            return ReturnMsg.success("编辑成功", permission);
        }
        return ReturnMsg.fail("编辑权限失败", null);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @GetMapping("/deletepermission")
    public ReturnMsg deletePermission(Integer id){
        if(id == null){
            return ReturnMsg.fail("id不能为空", null);
        }
        SysPermission permission = new SysPermission();
        permission.setId(id);
        int updateCount = sysPermissionService.deleteByPrimaryKey(permission);
        if(updateCount == 1){
            return ReturnMsg.success("删除成功", permission);
        }
        return ReturnMsg.fail("删除失败", null);
    }


    private ReturnMsg validateNull(Object... objects){
        for(Object obj: objects){
            if(obj == null){
                return ReturnMsg.fail("参数不能为空", null);
            }
        }
        return ReturnMsg.success("校验通过", null);
    }
}
