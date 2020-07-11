require "spaceship"

Spaceship.login('MP@quantdo.com.cn', 'Quantdo123456')

#参数传入true表示需要新增设备，例如：ruby UpdateProfile.rb true
if ARGV[0] == "true"
    file = File.open("multiple-device-upload-ios.txt") #文本文件里录入的udid和设备名用tab分隔
    puts "Updating #{p}"

    file.each do |line|
        arr = line.strip.split("\t")
        device = Spaceship.device.create!(name: arr[1], udid: arr[0])
        puts "add device: #{device.name} #{device.udid} #{device.model}"
    end

    devices = Spaceship.device.all
    puts "length:: #{devices.length}"

    profiles = Array.new
    profiles += Spaceship.provisioning_profile.development.all
#    profiles += Spaceship.provisioning_profile.ad_hoc.all

    profiles.each do |p|
        puts "Updating #{p.name}"
        p.devices = devices
        p.update!
    end
end

dic = {"qdinfini.com.cn.dev" => "qdinfini.com.cn.dev.mobileprovision",
    "qdinfini.com.cn2.dev" => "qdinfini.com.cn2.dev.mobileprovision",
    "qdinfini.com.cn2.NotificationService.dev" => "qdinfini.com.cn2.NotificationService.dev.mobileprovision",
    "qdinfini.com.NotificationService.dev" => "qdinfini.com.NotificationService.dev.mobileprovision",
    "wuchanjinshu.dev" => "wuchanjinshu.dev.mobileprovision"
}

downloadProfiles = Array.new
downloadProfiles += Spaceship.provisioning_profile.development.all
#downloadProfiles += Spaceship.provisioning_profile.ad_hoc.all

downloadProfiles.each do |p|
    puts "Hello, Ruby!";
#    puts "Downloading #{p.name}"
    fileName = dic[p.name]
    File.write("/Users/xiaobao/Library/MobileDevice/Provisioning Profiles/#{p.name}.mobileprovision", p.download)
end
