package utils;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

public class Helper {

    @SneakyThrows
    public static <T extends Container> T getContainer(SelenideElement element, Class<T> type) {
        return type.getConstructor(SelenideElement.class).newInstance(element);
    }

    public static <T extends Container> List<T> getContainerList(ElementsCollection elements, Class<T> type) {
        return elements.shouldHave(sizeGreaterThan(0))
                .asFixedIterable().stream()
                .map(el -> getContainer(el, type))
                .collect(Collectors.toList());
    }


}
