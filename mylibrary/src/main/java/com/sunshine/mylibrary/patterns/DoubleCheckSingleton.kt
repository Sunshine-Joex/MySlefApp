package com.sunshine.mylibrary.patterns

/**
 * @author SunShine-Joex
 * @date   2020/9/26
 * @desc    双重校验锁式（Double Check)
 */
class DoubleCheckSingleton private constructor() {
    companion object {
        val instance: DoubleCheckSingleton by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoubleCheckSingleton()
        }

    }
}