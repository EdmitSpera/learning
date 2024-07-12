```java
// 方案一
public class Main {

    public static void main(String[] args) {
        System.out.println(Josephus(6));
        System.out.println(Josephus(9));
        System.out.println(Josephus(13));
    }


    static boolean checkPowerOfTwo(int n) { // 判断二次幂
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    static int Josephus(int n) {
        if (checkPowerOfTwo(n) || n == 1) {
            return 1;
        } else {
            int temp = (int) (Math.log(n) / Math.log(2));    // 计算次幂的值
            int maxPowerOfTwo = (int) Math.pow(2, temp);
            return 2 * (n - maxPowerOfTwo) + 1;
        }
    }
}
```

```c
#include <stdio.h>
#include <stdlib.h>

// 定义士兵节点
typedef struct Soldier {
    int id; // 士兵编号
    struct Soldier* next; // 指向下一个士兵的指针
} Soldier;

// 创建循环链表
Soldier* createCircle(int n) {
    Soldier* head = NULL;
    Soldier* prev = NULL;
    
    // 创建第一个士兵
    head = (Soldier*)malloc(sizeof(Soldier));
    head->id = 1;
    head->next = NULL;
    prev = head;

    // 创建剩余的士兵节点并构建循环链表
    for (int i = 2; i <= n; i++) {
        Soldier* newSoldier = (Soldier*)malloc(sizeof(Soldier));
        newSoldier->id = i;
        newSoldier->next = NULL;
        prev->next = newSoldier;
        prev = newSoldier;
    }

    // 将最后一个士兵的next指向头节点，构成循环
    prev->next = head;

    return head;
}

// 执行规则并返回最后存活的士兵编号
int executeRules(int n) {
    int m = 2; // 默认间隔数为2
    Soldier* head = createCircle(n); // 创建士兵循环链表
    Soldier* current = head; // 当前士兵指针

    // 模拟规则，直到只剩下一个士兵
    while (current->next != current) {
        // 找到第m个士兵的前一个士兵
        for (int i = 1; i < m - 1; i++) {
            current = current->next;
        }

        // 移除第m个士兵
        Soldier* temp = current->next;
        current->next = temp->next;
        free(temp);

        // 继续下一个士兵
        current = current->next;
    }

    int lastSurvivor = current->id; // 最后存活的士兵编号
    free(current); // 释放最后存活的士兵节点

    return lastSurvivor;
}

int main() {
    int n;
    printf("input:");
    scanf("%d", &n);

    int lastSurvivor = executeRules(n);
    printf("output:%d\n", lastSurvivor);

    return 0;
}

```