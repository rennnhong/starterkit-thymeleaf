package indi.rennnhong.staterkit.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CopyBeanUtils {

    public static <S, T> void copyList(@NonNull List<S> sourceList, @NonNull List<T> targetList, Class<T> targetClz) {
        Preconditions.checkNotNull(sourceList);
        Preconditions.checkNotNull(targetList);

//        if (sourceList.size() < 1) targetList = Collections.emptyList();

        sourceList.forEach(item -> {
            T data = null;
            try {
                data = targetClz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(item, data);
            targetList.add(data);
        });
    }

    public static <S, T> List<T> copyList(@NonNull List<S> sourceList, Class<T> targetClz) {
        ArrayList<T> targetList = Lists.newArrayList();
        copyList(sourceList, targetList, targetClz);
        return targetList;
    }
}
