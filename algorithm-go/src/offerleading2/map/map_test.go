//
// @author Ls J
// @date 2020-05-30 23:21
//
package _map

import "testing"

func TestMap(t *testing.T) {
    map1 := map[int]int{0: 1, 1: 2}
    t.Log(len(map1))

    map2 := map[int]int{}
    map2[1] = 1
    t.Log(len(map2))

    // 第二个参数指定容量 cap
    map3 := make(map[int]int, 10)
    map3[1] = 1
    map3[2] = 2
    t.Log(len(map3))

    // 遍历，第一个是 key，第二个是 value
    for key, v := range map1 {
        t.Logf("key: %d, value: %d\n", key, v)
    }
}

func TestAccessNotExistingKey(t *testing.T) {
    map1 := map[int]int{}
    // 当访问的 key 不存在时，会返回一个零值，而不是空值
    t.Log(map1[1]) // 0
    map1[1] = 0
    t.Log(map1[1]) // 0

    // v -> value
    if v, ok := map1[1]; ok {
        t.Logf("key 1 exists, value: %d", v)
    } else {
        t.Log("key 1 does not exists")
    }

    if v, ok := map1[2]; ok {
        t.Logf("key 2 exists, value: %d", v)
    } else {
        t.Log("key 2 does not exists")
    }
}
