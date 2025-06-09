import SwiftUI
import composeApp
import AppTrackingTransparency
import FirebaseAnalytics
import Firebase
import AppTrackingTransparency
import Foundation

@main
class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate{
    var window: UIWindow?
    
    func applicationDidBecomeActive(_ application: UIApplication) {
        if #available(iOS 15.0, *) {
            ATTrackingManager.requestTrackingAuthorization(completionHandler: { status in
                
            })
        }
    }
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        FirebaseApp.configure()
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = Main_iosKt.MainViewController()
        window?.makeKeyAndVisible()
        
        return true
    }
}
