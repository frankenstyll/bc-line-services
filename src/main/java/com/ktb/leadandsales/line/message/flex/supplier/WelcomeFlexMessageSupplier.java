package com.ktb.leadandsales.line.message.flex.supplier;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexGravity;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class WelcomeFlexMessageSupplier implements Supplier<FlexMessage> {
	
	private String userId = "";
	
    @Override
    public FlexMessage get() {
        final Box headerBlock = createHeaderBlock();
        final Box heroBlock =  createHeroBlock();
       // final Box bodyBlock = createBodyBlock();
        final Box footerBlock =  createFooterBlock();
        final Bubble bubble = Bubble.builder()
                .header(headerBlock)
                .hero(heroBlock)
                //.body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("Welcome to RM Alert", bubble);
    }

    private Box createHeaderBlock() {
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .contents(asList(
                        Text.builder()
                                .text("RM ALERT")
                                .weight(Text.TextWeight.BOLD)
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM).build()
                )).build();
    }
    
    private Box createHeroBlock() {
    	return Box.builder()
    			.layout(FlexLayout.VERTICAL)
    			.flex(2)
    			.contents(asList(createHeaderImageBlock()))
    			.build();
    }
//    private Box createHeroBlock() {
//    	return Box.builder()
//                .layout(FlexLayout.VERTICAL)
//                .flex(2)
//                .contents(asList(
//                        Text.builder()
//                                .text(" DEMO RM ALERT "
//                                	)
//                                .weight(Text.TextWeight.BOLD)
//                                .gravity(FlexGravity.TOP)
//                                .size(FlexFontSize.XS)
//                                .color("#0066FF")
//                                .flex(1)
//                                .build()
//                ))
//                .build();
//    }
    
    private Box createFooterBlock() {
        URI uriIRegister = URI.create(
        		"https://glacial-peak-48383.herokuapp.com/register/rmregister?register=" + this.userId);
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .contents(asList(
                        Button.builder()
                                .action(new URIAction("สมัครรับสมาชิกรับข้อมูล"
                                		,uriIRegister, null))
                                .build()
                )).build();
    }

    
    private Image createHeaderImageBlock() {
        URI uriImage = URI.create("https://imgur.com/CPwJ2tj");
        return Image.builder()
                .url(uriImage)
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .build();
    }

    private Box createBodyBlock() {
        //final Box imageBlock = createThumbnailsBox();
        final Box heightLightBlock = createNewsBlock();
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .spacing(FlexMarginSize.MD)
//                .contents(asList(imageBlock, heightLightBlock))
                .contents(asList(heightLightBlock))
                .build();
    }
    
    private Box createThumbnailsBox() {
        URI uriImage = URI.create("https://2553d2b9.ngrok.io/img/thumbnail1.png");
        URI uriImage2 = URI.create("https://2553d2b9.ngrok.io/img/thumbnail2.png");
        URI uriExample = URI.create("http://www.iamnavanit.com");
        
        final Image imagesContent1 = Image.builder()
                .url(uriImage)
                .aspectMode(Image.ImageAspectMode.Cover)
                .aspectRatio(Image.ImageAspectRatio.R4TO3)
                .size(Image.ImageSize.SM)
                .gravity(FlexGravity.BOTTOM)
                .build();
        final Image imagesContent2 = Image.builder()
                .url(uriImage2)
                .aspectMode(Image.ImageAspectMode.Cover)
                .aspectRatio(Image.ImageAspectRatio.R4TO3)
                .size(Image.ImageSize.SM)
                .margin(FlexMarginSize.MD)
                .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .flex(1)
                .contents(asList(imagesContent1, imagesContent2))
                .build();
    }

    private Box createNewsBlock() {
        final Separator separator = Separator.builder().build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .flex(2)
                .contents(asList(
                        Text.builder()
                                .text("7 Things to Know for Today")
                                .gravity(FlexGravity.TOP)
                                .size(FlexFontSize.XS)
                                .flex(1)
                                .build(),
                        separator,
                        Text.builder()
                                .text("Hay fever goes wild")
                                .gravity(FlexGravity.CENTER)
                                .size(FlexFontSize.XS)
                                .flex(2)
                                .build(),
                        separator,
                        Text.builder()
                                .text("LINE Pay Begins Barcode Payment Service")
                                .gravity(FlexGravity.CENTER)
                                .size(FlexFontSize.XS)
                                .flex(2)
                                .build(),
                        separator,
                        Text.builder()
                                .text("LINE Adds LINE Wallet")
                                .gravity(FlexGravity.BOTTOM)
                                .size(FlexFontSize.XS)
                                .flex(1)
                                .build()
                ))
                .build();
    }


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


}
