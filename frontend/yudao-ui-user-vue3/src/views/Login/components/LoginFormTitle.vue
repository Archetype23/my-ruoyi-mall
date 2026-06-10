<template>
  <div class="mb-8 text-center">
    <h2 class="enter-x mb-3 text-center text-2xl font-bold xl:text-center xl:text-3xl">
      {{ title }}
    </h2>
    <div class="text-14px text-gray-400">社区团购后台管理平台</div>
  </div>
</template>
<script lang="ts" setup>
import { useAppStore } from '@/store/modules/app'
import { underlineToHump } from '@/utils'

defineOptions({ name: 'LoginFormTitle' })

const appStore = useAppStore()
const title = computed(() => underlineToHump(appStore.getTitle))
</script>
