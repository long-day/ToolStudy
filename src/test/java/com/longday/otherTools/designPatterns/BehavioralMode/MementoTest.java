package com.longday.otherTools.designPatterns.BehavioralMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @desc 备忘录模式
 * @date 2022/9/16
 */
public class MementoTest {
    /**
     * 优点:
     * 1.提供了-种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
     * 2.实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
     * 3.简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，并由管理者进行管理，
     * 这符合单一职责原则
     *
     * 缺点:
     * 1.资源消耗大。如果要保存的内部状态信息过多或者特别频繁，将会占用比较大的内存资源。
     *
     * 使用场景
     * 1.需要保存与恢复数据的场景，如玩游戏时的中间结果的存档功能。
     * 2.需要提供一个可回滚操作的场景，如记事本、Photoshop, idea等软件在编辑时按Ctrl+Z组合键，还有数据库中事务操作。
     */
    @Test
    public void memento(){
        System.out.println("------分割线------");
        GameRole gameRole = new GameRole();
        gameRole.initState();
        gameRole.stateDisplay();

        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setMemento(gameRole.saveState());

        System.out.println("------分割线------");
        gameRole.fight();
        gameRole.stateDisplay();

        System.out.println("------分割线------");
        gameRole.recoverState(roleStateCaretaker.getMemento());
        gameRole.stateDisplay();
    }
}

/**
 * 一个标识接口
 */
interface  Memento {

}

@NoArgsConstructor
@AllArgsConstructor
@Data
class RoleStateCaretaker{
    private  Memento memento;
}

@Data
class GameRole{
    private int vit;
    private int atk;
    private int def;

    public void initState(){
        this.vit = 100;
        this.atk = 100;
        this.def = 100;
    }

    public void fight(){
        this.vit = 0;
        this.atk = 0;
        this.def = 0;
    }

    public Memento saveState(){
        return new RoleStateMemento(vit, atk, def);
    }

    public void recoverState(Memento memento){
        RoleStateMemento roleStateMemento = (RoleStateMemento) memento;
        this.atk = roleStateMemento.getAtk();
        this.vit = roleStateMemento.getVit();
        this.def = roleStateMemento.getDef();
    }

    public void stateDisplay(){
        System.out.println("生命: "+this.vit);
        System.out.println("攻击力: "+this.atk);
        System.out.println("防御力: "+this.def);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class RoleStateMemento implements Memento{
        private int vit;
        private int atk;
        private int def;
    }
}