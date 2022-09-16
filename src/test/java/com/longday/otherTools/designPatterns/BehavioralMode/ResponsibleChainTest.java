package com.longday.otherTools.designPatterns.BehavioralMode;

import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @desc 责任链模式
 * @date 2022/9/14
 */
public class ResponsibleChainTest {

    /**
     * 优点:
     *  降低了对象之间的耦合度:    该模式降低了请求发送者和接收者的耦合度。
     *  增强了系统的可扩展性: 可以根据需要增加新请求处理类,满足开闭原则。
     *  增强了给对象指派职责的灵活性: 当工作流程发生变化，可以动态地改变链内的成员或者修改它们的次序，
     *                              也可动态地新增或者删除责任。
     *  责任链简化了对象之间的连接:  一个对象只需保持一个指向其后继者的引用，不需保持其他所有处理者的引用,
     *                              这避免了使用众多的if或者if...else语句。
     *  责任分担:   每个类只需要处理自己该处理的工作,不能处理的传递给下一个对象完成，明确各类的责任范围，
     *              符合类的单一职责原则。
     * 缺点:
     *  1.不能保证每个请求一 定被处理。由于一个请求没有明确的接收者，所以不能保证它一定会被处理，
     *    该请求可能一直传到链的末端都得不到处理。
     *  2.对比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响。
     *  3.职责链建立的合理性要靠客户端来保证，增加”了客户端的复杂性，可能会由于职责链的错误设置而导致系统出错，
     *    如可能会造成循环调
     */

    @Test
    public void responsibleChain(){
        LeaveRequest leaveRequest = new LeaveRequest("小明", 8, "去洗脚");

        GroupLeader groupLeader = new GroupLeader();
        ManagerLeader managerLeader = new ManagerLeader();
        GeneralManagerLeader generalManagerLeader = new GeneralManagerLeader();

        groupLeader.setNextHandel(managerLeader);
        managerLeader.setNextHandel(generalManagerLeader);

        groupLeader.submit(leaveRequest);
    }
}

/**
 * 请假条类
 */
@Getter
class LeaveRequest{
    private String name;
    private Integer leaveDays;
    private String Contain;

    public LeaveRequest(String name, Integer leaveDays, String contain) {
        this.name = name;
        this.leaveDays = leaveDays;
        Contain = contain;
    }

}

/**
 * 抽象处理者类
 */
abstract class Handler{
    protected final static int  LEAVE_ONE_DAY = 1;
    protected final static int  LEAVE_TWO_DAYS = 3;
    protected final static int  LEAVE_THREE_DAYS = 7;

    private Integer startDay;
    private Integer endDay;

    private Handler nextHandler;

    public Handler(Integer startDay) {
        this.startDay = startDay;
    }

    public Handler(Integer startDay, Integer endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    protected abstract void handleLeave(LeaveRequest leaveRequest);


    //设置下一级别处理对象
    public void setNextHandel(Handler leave) {
        this.nextHandler = leave;
    }

    //提交请假条
    public final void submit(LeaveRequest leaveRequest){
        if (leaveRequest.getLeaveDays()>this.endDay){
            System.out.println("无法处理，提交给下一处理者");
            if(nextHandler != null){
                this.nextHandler.submit(leaveRequest);
            }else {
                System.out.println("对不起，请求天数过多，请重新填写");
            }
        }else {
            handleLeave(leaveRequest);
        }

    }
}

/**
 * 小组长类
 */
class GroupLeader extends Handler{

    public GroupLeader() {
        super(0, Handler.LEAVE_ONE_DAY);
    }

    /**
     * @param leaveRequest 请假条
     */
    @Override
    protected void handleLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName()+"请假"+leaveRequest.getLeaveDays()+"天,"+leaveRequest.getContain());
        System.out.println("小组长同意");
    }
}


/**
 * 部门经理 类
 */
class ManagerLeader extends Handler{

    public ManagerLeader() {
        super(0, Handler.LEAVE_TWO_DAYS);
    }

    /**
     * @param leaveRequest 请假条
     */
    @Override
    protected void handleLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName()+"请假"+leaveRequest.getLeaveDays()+"天,"+leaveRequest.getContain());
        System.out.println("经理同意");
    }
}


/**
 * 总经理 类
 */
class GeneralManagerLeader extends Handler{

    public GeneralManagerLeader() {
        super(0, Handler.LEAVE_THREE_DAYS);
    }

    /**
     * @param leaveRequest 请假条
     */
    @Override
    protected void handleLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName()+"请假"+leaveRequest.getLeaveDays()+"天,"+leaveRequest.getContain());
        System.out.println("总经理同意");
    }
}