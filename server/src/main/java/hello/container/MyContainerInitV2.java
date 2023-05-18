package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class) // 해당 인터페이스(AppInit)의 구현체들을 전부 조회
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        // 구현체들이 Set으로 넘어옴
        // Class 정보가 전달되기 때문에 객체 생성 필요
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("c = " + c);
        System.out.println("ctx = " + ctx);

        for (Class<?> appInitClass : c) {
            try {
                // new AppInitV1Servlet()과 같은 코드
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
