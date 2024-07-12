## 1. Key 名设计
### (1) 建议：可读性和可管理性
- 以业务名(或数据库名)为前缀，防止 key 冲突，用冒号分隔。
- 示例：`ugc:video:1`

### (2) 建议：简洁性
- 在保证语义的前提下，控制 key 的长度，减少内存占用。
- 示例：`user:{uid}:friends:messages:{mid}` 简化为 `u:{uid}:fr:m:{mid}`。

### (3) 强制：不要包含特殊字符
- 反例：包含空格、换行、单双引号及其他转义字符。

## 2. Value 设计
### (1) 强制：拒绝 bigkey（防止网卡流量过大、慢查询）
- string 类型控制在 10KB 以内，hash、list、set、zset 元素个数不要超过 5000。
- 反例：一个包含 200 万个元素的 list。
- 非字符串的 bigkey 不要使用 `DEL` 删除，使用 `HSCAN`、`SSCAN`、`ZSCAN` 渐进式删除。同时防止 bigkey 过期时间自动删除问题。
    - 示例：一个 200 万的 zset 设置 1 小时过期，会触发 `DEL` 操作，造成阻塞，而且该操作不会出现在慢查询中（`LATENCY` 可查）。
    - 查找方法和删除方法。

### (2) 推荐：选择适合的数据类型
- 示例：
    - 反例：
      ```shell
      SET user:1:name tom
      SET user:1:age 19
      SET user:1:favor football
      ```
    - 正例：
      ```shell
      HMSET user:1 name tom age 19 favor football
      ```

## 3. 推荐：控制 key 的生命周期，Redis 不是垃圾桶
- 建议使用 `EXPIRE` 设置过期时间（条件允许可以打散过期时间，防止集中过期）。
- 不过期的数据重点关注 `IDLETIME`。