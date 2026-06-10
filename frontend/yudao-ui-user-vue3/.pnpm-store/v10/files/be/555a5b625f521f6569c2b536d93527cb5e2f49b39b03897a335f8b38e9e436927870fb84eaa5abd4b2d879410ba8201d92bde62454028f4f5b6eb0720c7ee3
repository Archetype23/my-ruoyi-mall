/**
 * @description video menu config
 * @author wangfupeng
 */
import { IUploadVideoConfig } from '@wangeditor-next/core';
import { VideoElement } from '../custom-types';
export declare function genUploadVideoMenuConfig(): IUploadVideoConfig;
/**
 * 生成插入网络视频的配置
 */
export declare function genInsertVideoMenuConfig(): {
    onInsertedVideo(_node: VideoElement): void;
    /**
     * 检查 video ，支持 async
     * @param src src
     * @param poster poster
     */
    checkVideo(_src: string, _poster: string): boolean | string | undefined;
    /**
     * 转换 video src
     * @param src src
     * @returns new src
     */
    parseVideoSrc(src: string): string;
};
