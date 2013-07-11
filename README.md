redis-research
==============
1. skip-list FROM http://blog.angryfox.com/?p=2089
2. 少许修改 ,一个bug。

FROM :while((rand()&0xFFFF) < (0.5 * 0xFFFF))
TO   :while((rand()&RAND_MAX) < (0.5 * RAND_MAX))

在vc++内，RAND_MAX = 0x7ffff，而不是 0xFFFF


