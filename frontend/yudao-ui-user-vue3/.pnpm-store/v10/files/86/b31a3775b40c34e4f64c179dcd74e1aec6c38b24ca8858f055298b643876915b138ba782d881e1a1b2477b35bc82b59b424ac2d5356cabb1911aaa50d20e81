/**
 * @description video menu
 * @author wangfupeng
 */
import EditorVideoSizeMenu from './EditVideoSizeMenu';
import EditorVideoSrcMenu from './EditVideoSrcMenu';
import InsertVideoMenu from './InsertVideoMenu';
import UploadVideoMenu from './UploadVideoMenu';
export declare const insertVideoMenuConf: {
    key: string;
    factory(): InsertVideoMenu;
    config: {
        onInsertedVideo(_node: import("../custom-types").VideoElement): void;
        checkVideo(_src: string, _poster: string): boolean | string | undefined;
        parseVideoSrc(src: string): string;
    };
};
export declare const uploadVideoMenuConf: {
    key: string;
    factory(): UploadVideoMenu;
    config: import("@wangeditor-next/core").IUploadConfig;
};
export declare const editorVideoSizeMenuConf: {
    key: string;
    factory(): EditorVideoSizeMenu;
};
export declare const editorVideoSrcMenuConf: {
    key: string;
    factory(): EditorVideoSrcMenu;
};
