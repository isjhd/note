import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler, LabelEncoder

# ---------------------- 步骤2：读取数据 ----------------------
df = pd.read_excel("水质检测.xlsx")

# ---------------------- 步骤3：缺失值处理 ----------------------
# 1. 用0填补"叶绿素a（mg/L）"和"藻密度（cells/L）"的缺失值
df["叶绿素a（mg/L）"] = df["叶绿素a（mg/L）"].fillna(0)
df["藻密度（cells/L）"] = df["藻密度（cells/L）"].fillna(0)

# 2. 删除"水质类别"缺失的行
df = df.dropna(subset=["水质类别"])

# 3. 用均值填补"电导率（μS/cm）"和"浊度（NTU）"的缺失值
df["电导率（μS/cm）"] = df["电导率（μS/cm）"].fillna(df["电导率（μS/cm）"].mean())
df["浊度（NTU）"] = df["浊度（NTU）"].fillna(df["浊度（NTU）"].mean())

# ---------------------- 步骤4：PH值异常值处理 ----------------------
# 箱形图查看异常值
plt.figure(figsize=(8, 4))
plt.boxplot(df["PH值"], vert=False, patch_artist=True)
plt.title("PH值箱形图")
plt.xlabel("PH值")
plt.show()

# 删除PH<0或PH>14的异常值
df = df[(df["PH值"] >= 0) & (df["PH值"] <= 14)]

# ---------------------- 步骤5：水温分布直方图 ----------------------
plt.figure(figsize=(8, 4))
plt.hist(df["水温（℃）"], bins=10, edgecolor="black")
plt.title("水温分布直方图")
plt.xlabel("水温（℃）")
plt.ylabel("频数")
plt.show()

# ---------------------- 步骤6：最小-最大规范化 ----------------------
cols_to_norm = [
    "溶解氧（mg/L）", "电导率（μS/cm）", "高锰酸盐指数（mg/L）",
    "氨氮（mg/L）", "总磷（mg/L）", "总氮（mg/L）",
    "叶绿素a（mg/L）", "藻密度（cells/L）"
]
scaler = MinMaxScaler(feature_range=(0, 1))
df[cols_to_norm] = scaler.fit_transform(df[cols_to_norm])

# ---------------------- 步骤7：PH值离散化 ----------------------
def ph_label(ph):
    if ph <= 6.5:
        return "过酸"
    elif ph <= 8.5:
        return "正常"
    else:
        return "过碱"

df["PH值标签"] = df["PH值"].apply(ph_label)

# ---------------------- 步骤8：水质类别标签编码 ----------------------
le = LabelEncoder()
df["水质类别_编码"] = le.fit_transform(df["水质类别"])

# ---------------------- 步骤9：相关性分析 ----------------------
corr_cols = [
    "水温（℃）", "PH值", "溶解氧（mg/L）", "电导率（μS/cm）", "浊度（NTU）",
    "高锰酸盐指数（mg/L）", "氨氮（mg/L）", "总磷（mg/L）", "总氮（mg/L）",
    "叶绿素a（mg/L）", "藻密度（cells/L）", "水质类别_编码"
]
corr_matrix = df[corr_cols].corr()
print("相关性矩阵：")
print(corr_matrix)

# ---------------------- 步骤10：输出预处理后文件 ----------------------
df.to_excel("水质检测_预处理.xlsx", index=False)
print("预处理完成，已保存为'水质检测_预处理.xlsx'")