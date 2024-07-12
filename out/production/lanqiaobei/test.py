def min_time_to_connect(n, points):
    # 构建一个字典，将坐标映射到水滴编号
    drops = {}
    for i in range(n):
        x, y = points[i]
        drops[(x, y)] = i

    # 定义四个方向的偏移量
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    # 初始化并查集
    parent = [i for i in range(n)]

    # 将每个水滴与其周围的四个水滴进行合并
    for x, y in points:
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if (nx, ny) in drops:
                merge(parent, drops[(x, y)], drops[(nx, ny)])

    # 统计连通块的数量
    count = 0
    for i in range(n):
        if parent[i] == i:
            count += 1

    # 返回最少需要的秒数
    return 2 if count > 1 else -1

def merge(parent, x, y):
    # 找到 x 和 y 的根节点
    x = find(parent, x)
    y = find(parent, y)

    # 将 y 的根节点合并到 x 的根节点下
    if x != y:
        parent[y] = x

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]


# 示例输入
n = 3
points = [(1, 1), (2, 2), (3, 4)]

# 输出结果
print(min_time_to_connect(n, points)) #Output: 2

