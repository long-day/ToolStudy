package com.longday.otherTools.designPatterns.structuralMode;

import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/8
 */
public class BridgeTest {
    @Test
    public void bridge(){
        WindowsSystem windowsSystem = new WindowsSystem(new AVIFile());
        windowsSystem.playVideo("花园宝宝.avi");
        System.out.println("============");
        WindowsSystem windowsSystem2 = new WindowsSystem(new RmvbFile());
        windowsSystem2.playVideo("天线宝宝.rmvb");
        System.out.println("------------");
        MacSystem macSystem = new MacSystem(new AVIFile());
        macSystem.playVideo("abc-123.avi");
        System.out.println("============");
        MacSystem macSystem2 = new MacSystem(new RmvbFile());
        macSystem2.playVideo("abc-456.rmvb");
    }

    /**
     * 好处:
     *      1.桥接模式提高了系统的可扩充性,在两个变化维度中任意扩展一个维度,都不需要修改原有系统。
     *          如:如果现在还有一种视频文件类型wmy,我们只需要再定义一个类实现videoFile接口即可,其他类不需要发生变化
     *      2.实现细节对客户透明
     * 使用场景:
     *      1.当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
     *      2.当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
     *      3.当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。避免在两个层次之间建立静态的继承联系,
     *        通过桥接模式可以使它们在抽象层建立一个关联关系。
     */
    @Test
    public void advantageAndApplicationScenarios(){}
}

/**
 * 实现化角色(抽象)
 */
interface VideoFile{
    void decode(String fileName);
}

/**
 * 具体的实现化角色(AVI)
 */
class AVIFile implements VideoFile{

    /**
     * @param fileName #
     */
    @Override
    public void decode(String fileName) {
        System.out.println("AVI视频文件解码: "+fileName);
    }
}

/**
 * 具体的实现化角色(RmvbFile)
 */
class RmvbFile implements VideoFile{

    /**
     * @param fileName #
     */
    @Override
    public void decode(String fileName) {
        System.out.println("Rmvb视频文件解码: "+fileName);
    }
}

/**
 * 抽象化角色(抽象类/也可是接口)
 */
abstract class OperatingSystem{
    protected VideoFile videoFile;

    public OperatingSystem(VideoFile videoFile) {
        this.videoFile = videoFile;
    }
    public abstract void playVideo(String filePath);
}

/**
 * 具体的 抽象化角色(Win)
 */
class WindowsSystem extends OperatingSystem{

    public WindowsSystem(VideoFile videoFile) {
        super(videoFile);
    }

    /**
     *播放视频
     */
    @Override
    public void playVideo(String filePath) {
        System.out.println("WindowsSystem 播放视频");
        videoFile.decode(filePath);
    }
}

/**
 * 具体的 抽象化角色(Mac)
 */
class MacSystem extends OperatingSystem{

    public MacSystem(VideoFile videoFile) {
        super(videoFile);
    }

    /**
     *播放视频
     */
    @Override
    public void playVideo(String filePath) {
        System.out.println("MacSystem 播放视频");
        videoFile.decode(filePath);
    }
}