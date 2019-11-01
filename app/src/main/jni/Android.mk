LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# 模块名称 luajava
LOCAL_MODULE := luajava

# 导入头文件到编译系统
LOCAL_C_INCLUDES := $(LOCAL_PATH)/lua $(LOCAL_PATH)/luajava

# 定义查找源文件的目录
MY_FILES_PATH  :=  $(LOCAL_PATH)/lua $(LOCAL_PATH)/luajava
# 过滤规则
MY_FILES_SUFFIX := %.cpp %.c %.cc

# 查找源文件.
My_All_Files := $(foreach src_path,$(MY_FILES_PATH), $(shell find "$(src_path)" -type f) )
My_All_Files := $(My_All_Files:$(MY_CPP_PATH)/./%=$(MY_CPP_PATH)%)
MY_SRC_LIST  := $(filter $(MY_FILES_SUFFIX),$(My_All_Files))
MY_SRC_LIST  := $(MY_SRC_LIST:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES := $(MY_SRC_LIST)
#打印编译信息
#$(warning 'src_list='$(LOCAL_SRC_FILES))

LOCAL_LDLIBS := -llog

# 生成动态库
include $(BUILD_SHARED_LIBRARY)