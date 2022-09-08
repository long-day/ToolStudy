package com.longday.otherTools.designPatterns.structuralMode;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 君
 * @version 1.0
 * @desc 组合模式
 * @date 2022/9/8
 */
public class CombinedTest {
    /**
     * 优点
     *      1.组合模式可以清楚的定义分层次的复杂对象，表示对象的全部或部分层次，它让客户端忽略了层次的差异,
     *          方便对整个层次结构进行控制。
     *      2.客户端可以一致地使用一个组合结构或其中单个对象，不必关心处理的是单个对象还是整个组合结构，简化了客户端代码。
     *      3.在组合模式中增加新的树枝节点和叶子节点都很方便，无须对现有类库进行任何修改，符合"开闭原则”。
     *      4.组合模式为树形结构的面向对象实现提供了一种灵活地解决方案,通过叶子节点和树枝节点的递归组合，可以形成
     *          复杂的树形结构,但对树形结构的控制却非常简单。
     * 使用场景
     *      1.组合模式正是应树形结构而生,所以组合模式的使用场景就是出现树形结构的地方。比如:文件目录显示，
     *      2.多级目录呈现等树形结构数据的操作。
     */
    @Test
    public void combined(){
        Menu menuSystem = new Menu("系统管理", 1);
        MenuComponent menuManage = new Menu("菜单管理", 2);
        MenuComponent menuTo = new MenuItem("页面访问", 3);
        MenuComponent menuAdd = new MenuItem("增加菜单", 3);
        MenuComponent menuRemove = new MenuItem("删除菜单", 3);
        MenuComponent menuUpdate = new MenuItem("更新菜单", 3);
        menuManage.add(menuTo);
        menuManage.add(menuAdd);
        menuManage.add(menuRemove);
        menuManage.add(menuUpdate);

        MenuComponent menuAuthority = new Menu("权限配置", 2);
        MenuComponent menuTo2 = new MenuItem("页面访问", 3);
        MenuComponent menuSave = new MenuItem("提交保存", 3);

        menuAuthority.add(menuTo2);
        menuAuthority.add(menuSave);


        MenuComponent menuRole = new Menu("角色管理", 2);
        MenuComponent menuAddRole = new MenuItem("增加角色", 3);

        menuRole.add(menuAddRole);

        menuSystem.add(menuManage);
        menuSystem.add(menuAuthority);
        menuSystem.add(menuRole);

        menuSystem.print();

        System.out.println("-----------");

        MenuComponent chile = menuSystem.getChile(1);
        chile.print();

    }
}

/**
 * 菜单组件抽象类
 */
@AllArgsConstructor
abstract class MenuComponent{
    protected String name;
    protected Integer level;

    /**
     * 添加菜单
     * @param component 新菜单
     */
    public void add(MenuComponent component){
        throw new UnsupportedOperationException();
    }

    /**
     * 移除菜单
     * @param component 要删除的菜单
     */
    public void remove(MenuComponent component){
        throw new UnsupportedOperationException();
    }

    /**
     * 获取指定子菜单
     * @param index 返回菜单
     */
    public MenuComponent getChile(Integer index){
        throw new UnsupportedOperationException();
    }

    /**
     * 获取菜单名称
     */
    public String getName(){
        return name;
    }

    /**
     * 打印菜单
     */
    public abstract void print();
}

class Menu extends MenuComponent{
    private final List<MenuComponent> menuComponentList = new ArrayList<>();

    public Menu(String name,Integer level){
        super(name,level);
    }

    /**
     * @param component 新菜单
     */
    @Override
    public void add(MenuComponent component) {
        menuComponentList.add(component);
    }

    /**
     * @param component 要删除的菜单
     */
    @Override
    public void remove(MenuComponent component) {
        menuComponentList.remove(component);
    }

    /**
     * @param index 返回菜单
     * @return MenuComponent
     */
    @Override
    public MenuComponent getChile(Integer index) {
        return menuComponentList.get(index);
    }

    /**
     * 打印菜单
     */
    @Override
    public void print() {
        //打印菜单名称
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(name);
        for (MenuComponent menuComponent : menuComponentList) {
            menuComponent.print();
        }

    }
}

class MenuItem extends MenuComponent{

    public MenuItem(String name, Integer level) {
        super(name, level);
    }

    /**
     *
     */
    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(name);
    }
}