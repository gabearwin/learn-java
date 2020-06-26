package xyz.gabear.learn.minispringone.service;

import java.util.Map;

public interface StudyService {
    Integer insert(Map map);

    Integer delete(Map map);

    Integer update(Map map);

    Integer select(Map map);
}
