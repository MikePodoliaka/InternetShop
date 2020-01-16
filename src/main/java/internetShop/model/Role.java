package internetShop.model;

public class Role {
    private RoleName roleName;
    private static Long idGenerator = 0L;
    private Long roleId;

    public Role() {

        roleId = idGenerator++;
    }
    public Role(RoleName roleName){
        this();
        this.roleName=roleName;
        idGenerator++;
    }

    public enum RoleName {
        USER, ADMIN;
    }

    public static Role of(String roleName){
        return new Role(RoleName.valueOf(roleName));
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


}
